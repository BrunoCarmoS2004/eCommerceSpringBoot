import { Routes } from '@angular/router';
import { RegisterComponent } from './pages/register/register.component';
import { HomeComponent } from './pages/home/home.component';
import { CadastroLoginComponent } from './pages/cadastro-login/cadastro-login.component';

export const routes: Routes = [
  {
    path:"auth/cadastro",
    component:RegisterComponent
  },
  {
    path:"auth/acesso",
    component:CadastroLoginComponent
  },
  {
    path:"home",
    component:HomeComponent
  },
  /*{
    path:"**",
    redirectTo:"home", pathMatch: "full"
  }*/
  {
    path:"**",
    redirectTo:"auth/acesso", pathMatch: "full"
  }
];
