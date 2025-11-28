import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService, Usuario } from '../../services/auth.service';

@Component({
  selector: 'app-perfil',
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {
  usuario: Usuario | null = null;
  loading = false;
  error = '';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    // Verificar si está logueado antes de cargar el perfil
    if (!this.authService.isLoggedIn()) {
      this.router.navigate(['/login']);
      return;
    }
    this.cargarPerfil();
  }

  cargarPerfil(): void {
    this.loading = true;
    this.error = '';

    this.authService.getPerfil().subscribe({
      next: (data: Usuario) => {
        this.usuario = data;
        this.loading = false;
      },
      error: (err: any) => {
        this.loading = false;
        
        if (err.status === 401 || err.status === 403) {
          this.error = 'Sesión expirada. Por favor, inicia sesión nuevamente.';
          this.authService.logout();
          setTimeout(() => {
            this.router.navigate(['/login']);
          }, 2000);
        } else {
          this.error = 'Error cargando perfil. Intenta nuevamente.';
          console.error('Error cargando perfil:', err);
        }
      }
    });
  }
}