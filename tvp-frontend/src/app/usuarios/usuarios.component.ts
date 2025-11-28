import { Component, OnInit } from '@angular/core';
import { AuthService, Usuario } from '../services/auth.service';

@Component({
  selector: 'app-usuarios',
template: `
  <div class="usuarios-container">
    <h2>Usuarios Registrados</h2>
    
    <div *ngIf="loading" class="loading">Cargando usuarios...</div>
    
    <div *ngIf="error" class="error">
      {{ error }}
    </div>

    <div class="usuarios-list">
      <div *ngFor="let usuario of usuarios" class="usuario-card">
        <h3>{{ usuario.email }}</h3>  <!-- ← CAMBIADO: username → email -->
        <p>ID: {{ usuario.id }}</p>
        <p>Rol: {{ usuario.rol }}</p>  <!-- ← AGREGADO: mostrar el rol -->
      </div>
    </div>

    <div *ngIf="usuarios.length === 0 && !loading" class="no-users">
      No hay usuarios registrados
    </div>
  </div>
`,
  styles: [`
    .usuarios-container {
      max-width: 800px;
      margin: 20px auto;
      padding: 20px;
    }
    .usuarios-list {
      display: grid;
      gap: 15px;
    }
    .usuario-card {
      border: 1px solid #ddd;
      padding: 15px;
      border-radius: 8px;
      background: #f9f9f9;
    }
    .loading {
      text-align: center;
      color: #007bff;
    }
    .error {
      color: red;
      text-align: center;
    }
    .no-users {
      text-align: center;
      color: #6c757d;
    }
  `]
})
export class UsuariosComponent implements OnInit {
  usuarios: Usuario[] = [];
  loading = false;
  error = '';

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios(): void {
    this.loading = true;
    this.authService.getUsuarios().subscribe({
      next: (data) => {
        this.usuarios = data;
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Error cargando usuarios';
        this.loading = false;
        console.error('Error:', err);
      }
    });
  }
}