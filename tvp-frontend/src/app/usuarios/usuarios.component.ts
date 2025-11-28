import { Component, OnInit } from '@angular/core';
import { AuthService, Usuario } from '../services/auth.service';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
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