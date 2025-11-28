import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { UsuariosComponent } from './components/usuarios/usuarios.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { PaquetesComponent } from './components/paquetes/paquetes.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { 
    path: 'usuarios', 
    component: UsuariosComponent, 
    canActivate: [AuthGuard]  // ← PROTEGER ESTA RUTA
  },
  { path: 'perfil', component: PerfilComponent, canActivate: [AuthGuard] },
  { path: 'paquetes', component: PaquetesComponent, canActivate: [AuthGuard] },
  { path: '', redirectTo: '/usuarios', pathMatch: 'full' }, // Redirigir a usuarios por defecto
  { path: '**', redirectTo: '/usuarios' } // Ruta comodín
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }