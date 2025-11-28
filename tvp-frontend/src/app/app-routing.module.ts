import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { AuthGuard } from './guards/auth.guard';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { 
    path: 'usuarios', 
    component: UsuariosComponent, 
    canActivate: [AuthGuard]  // ← PROTEGER ESTA RUTA
  },
  { path: '', redirectTo: '/usuarios', pathMatch: 'full' }, // Redirigir a usuarios por defecto
  { path: '**', redirectTo: '/usuarios' } // Ruta comodín
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }