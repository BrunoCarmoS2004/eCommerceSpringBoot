import { FormControl } from "@angular/forms";

export interface UsuarioRegisterForm{
  email:string;
  password:string;
  confirmPassword:string;
  nome:string;
  cpf:string;
  sobreNome:string;
  numeroTelefone:string;
}
