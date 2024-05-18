import { HttpHeaders } from "@angular/common/http";

export class environment{
  pathLoginRegister:string = "http://localhost:8080/auth";
  pathUsuario:string = "http://localhost:8080/usuario";
  pathAdmin:string = "http://localhost:8080/admin";
  pathBusca:string = "http://localhost:8080/buscar";
  pathCliente:string = "http://localhost:8080/cliente";
  pathEndereco:string = "http://localhost:8080/endereco";
  pathVendas:string = "http://localhost:8080/produto";
  pathVendedor:string = "http://localhost:8080/vendedor";
  
  getOptions(): any {
    var token: string | null = sessionStorage.getItem("user-token");
    var headers = new HttpHeaders({'Authorization': `Bearer ${token}`});
    var options = { headers: headers };
    return options;
}
}
