export class UsuarioRegister{
  email:string = "";
  password:string = "";
  nome:string = "";
  sobreNome:string = "";
  numeroTelefone:string = "";
  cpf:string = "";
  role:string;
  cep:string = "";
  logradouro:string = "";
  complemento:string = "";
  bairro:string = "";
  localidade:string = "";
  uf:string = "";
  pais:string = "";
  constructor(role: string){
    this.role = role;
  }
}
