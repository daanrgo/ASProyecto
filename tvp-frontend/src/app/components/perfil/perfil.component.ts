import { Component, OnInit } from '@angular/core';
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

  constructor(private authService: AuthService) {}

  ngOnInit(): void {
    this.cargarPerfil();
  }

  cargarPerfil(): void {
    this.loading = true;
    this.authService.getPerfil().subscribe({
      next: (data: Usuario) => {
        this.usuario = data;
        this.loading = false;
      },
      error: (err: any) => {
        this.error = 'Error cargando perfil';
        this.loading = false;
        console.error('Error:', err);
      }
    });
  }
}