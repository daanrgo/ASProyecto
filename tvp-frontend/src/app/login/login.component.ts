import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, LoginRequest } from '../services/auth.service';

@Component({
  selector: 'app-login',
  template: `
    <div class="login-container">
      <h2>Login TVP</h2>
      <form (ngSubmit)="onSubmit()">
        <div class="form-group">
          <label>Email:</label>
          <input 
            type="email" 
            [(ngModel)]="credentials.email" 
            name="email" 
            required
            placeholder="Ingresa tu email">
        </div>
        
        <div class="form-group">
          <label>Contraseña:</label>
          <input 
            type="password" 
            [(ngModel)]="credentials.password" 
            name="password" 
            required
            placeholder="Ingresa tu contraseña">
        </div>
        
        <button type="submit" [disabled]="loading">
          {{ loading ? 'Cargando...' : 'Ingresar' }}
        </button>
        
        <div *ngIf="error" class="error-message">
          {{ error }}
        </div>
      </form>
    </div>
  `,
  styles: [`
    .login-container {
      max-width: 400px;
      margin: 50px auto;
      padding: 20px;
      border: 1px solid #ddd;
      border-radius: 8px;
    }
    .form-group {
      margin-bottom: 15px;
    }
    label {
      display: block;
      margin-bottom: 5px;
    }
    input {
      width: 100%;
      padding: 8px;
      border: 1px solid #ccc;
      border-radius: 4px;
    }
    button {
      width: 100%;
      padding: 10px;
      background: #007bff;
      color: white;
      border: none;
      border-radius: 4px;
      cursor: pointer;
    }
    button:disabled {
      background: #6c757d;
    }
    .error-message {
      color: red;
      margin-top: 10px;
    }
  `]
})
export class LoginComponent {
  credentials: LoginRequest = { email: '', password: '' };
  loading = false;
  error = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  onSubmit(): void {
    this.loading = true;
    this.error = '';

    this.authService.login(this.credentials).subscribe({
      next: (response) => {
        this.loading = false;
        this.router.navigate(['/usuarios']);
      },
      error: (err) => {
        this.loading = false;
        this.error = 'Error de autenticación. Verifica tus credenciales.';
        console.error('Login error:', err);
      }
    });
  }
}