import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'; // ← Agregar HttpHeaders
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

export interface LoginRequest {
  email: string;     
  password: string;
}

export interface LoginResponse {
  token: string;
  message: string;
  usuario: Usuario;
}

export interface Usuario {
  id: number;
  email: string;
  rol: string;
}

export interface RegistroRequest {
  email: string;
  password: string;
  rol?: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private authUrl = 'http://localhost:8083/auth';
  private currentUserSubject = new BehaviorSubject<Usuario | null>(null);

  constructor(private http: HttpClient) {
    this.loadStoredUser();
  }

  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.authUrl}/login`, credentials)
      .pipe(
        tap(response => {
          if (response.token) {
            localStorage.setItem('auth_token', response.token);
            localStorage.setItem('user_data', JSON.stringify(response.usuario));
            this.currentUserSubject.next(response.usuario);
          }
        })
      );
  }

  registrar(usuarioData: RegistroRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.authUrl}/register`, usuarioData);
  }

  getUsuarios(): Observable<Usuario[]> {
    const token = this.getToken();
    
    // Crear headers con el token de autorización
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    return this.http.get<Usuario[]>(`${this.authUrl}/usuarios`, { headers });
  }

  logout(): void {
    localStorage.removeItem('auth_token');
    localStorage.removeItem('user_data');
    this.currentUserSubject.next(null);
  }

  getCurrentUser(): Observable<Usuario | null> {
    return this.currentUserSubject.asObservable();
  }

  isLoggedIn(): boolean {
    return !!this.getToken() && this.isTokenValid();
  }

  // obtener el token
  getToken(): string | null {
    return localStorage.getItem('auth_token');
  }

  // verificar si el token es válido
  isTokenValid(): boolean {
    const token = this.getToken();
    if (!token) return false;
    
    try {
      // Decodificar el token JWT (parte del payload)
      const payload = JSON.parse(atob(token.split('.')[1]));
      const expiration = payload.exp * 1000; // Convertir a milisegundos
      return Date.now() < expiration;
    } catch (error) {
      return false;
    }
  }

  private loadStoredUser(): void {
    try {
      const userData = localStorage.getItem('user_data');
      if (userData && userData !== 'undefined' && userData !== 'null') {
        const user = JSON.parse(userData);
        this.currentUserSubject.next(user);
      }
    } catch (error) {
      console.error('Error loading stored user:', error);
      localStorage.removeItem('user_data');
      localStorage.removeItem('auth_token');
    }
  }
}