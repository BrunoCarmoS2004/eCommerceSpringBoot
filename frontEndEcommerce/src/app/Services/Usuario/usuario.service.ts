import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsuarioRegister } from '../../Models/Usuarios/usuarioRegister.model';
import { environment } from '../../../assets/environment';
import { UsuarioLogin } from '../../Models/Usuarios/usuarioLogin.model';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { error } from 'console';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  pathApiUrl:environment
  authorities:string|number = "";
  constructor(private http:HttpClient) {
    this.pathApiUrl= new environment();
  }
  registro(usuarioRegisterObj: UsuarioRegister): Observable<any> {
    return this.http.post(this.pathApiUrl.pathLoginRegister + "/register", usuarioRegisterObj).pipe(
      catchError(error => this.handleError(error))
    );
  }

  login(usuarioLoginObj:UsuarioLogin):Observable<any>{
    return this.http.post(this.pathApiUrl.pathLoginRegister+"/login",usuarioLoginObj).pipe(
      tap((value:any)=>{
        sessionStorage.setItem("user-token",value.token)
        sessionStorage.setItem("user-email",value.email)
        catchError(error => this.handleError(error))
      })
    )
  }

  excluirDados(){
    sessionStorage.removeItem("user-token")
    sessionStorage.removeItem("user-email")
    //sessionStorage.removeItem("user-role")
  }
  logout(){
    this.http.get(this.pathApiUrl.pathLoginRegister+"/logout").pipe(
      catchError(error => this.handleError(error))
    ).subscribe()
  }

  handleError(error:any) {
    console.error('Ocorreu um erro!', error);
    return throwError(() => error);
  }

  verificarEmailEmUso(email: string): Observable<any> {
    if (!email || email.trim() === '') {
      return throwError(()=> new Error('Email não pode ser vazio'));
    }
    return this.http.get(this.pathApiUrl.pathUsuario+"/verificar/email/"+email)
  }
  verificarCpfEmUso(cpf:string):Observable<any>{
    if (!cpf || cpf.trim() === '') {
      return throwError(()=> new Error('Cpf não pode ser vazio'));
    }
    return this.http.get(this.pathApiUrl.pathUsuario+"/verificar/cpf/"+cpf);
  }
}
