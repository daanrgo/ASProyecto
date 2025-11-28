import { Component, OnInit } from '@angular/core';

export interface Paquete {
  id: number;
  nombre: string;
  destino: string;
  descripcion: string;
  precio: number;
  duracion: string;
  imagen: string;
  disponible: boolean;
}

@Component({
  selector: 'app-paquetes',
  templateUrl: './paquetes.component.html',
  styleUrls: ['./paquetes.component.css']
})
export class PaquetesComponent implements OnInit {
  paquetes: Paquete[] = [];
  loading = false;
  error = '';

  ngOnInit(): void {
    this.cargarPaquetes();
  }

  cargarPaquetes(): void {
    this.loading = true;
    // Simulamos datos de paquetes - aquí conectarías con tu backend
    setTimeout(() => {
      this.paquetes = [
        {
          id: 1,
          nombre: 'Tour Europa Clásica',
          destino: 'Paris, Roma, Londres',
          descripcion: 'Descubre las capitales más emblemáticas de Europa en un tour completo.',
          precio: 2500,
          duracion: '15 días',
          imagen: '/assets/europa.jpg',
          disponible: true
        },
        {
          id: 2,
          nombre: 'Caribe Paradise',
          destino: 'Cancún, Punta Cana',
          descripcion: 'Disfruta de las playas más hermosas del Caribe con todo incluido.',
          precio: 1800,
          duracion: '10 días',
          imagen: '/assets/caribe.jpg',
          disponible: true
        },
        {
          id: 3,
          nombre: 'Aventura Asiática',
          destino: 'Tokio, Bangkok, Bali',
          descripcion: 'Explora la cultura y paisajes exóticos del continente asiático.',
          precio: 3200,
          duracion: '20 días',
          imagen: '/assets/asia.jpg',
          disponible: false
        },
        {
          id: 4,
          nombre: 'Sudamérica Express',
          destino: 'Machu Picchu, Rio de Janeiro',
          descripcion: 'Conoce los destinos más fascinantes de Sudamérica.',
          precio: 1500,
          duracion: '12 días',
          imagen: '/assets/sudamerica.jpg',
          disponible: true
        }
      ];
      this.loading = false;
    }, 1000);
  }

  reservarPaquete(paquete: Paquete): void {
    if (paquete.disponible) {
      // Aquí iría la lógica para reservar el paquete
      alert(`Reservando: ${paquete.nombre}`);
    }
  }
}