import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { EnderecoGet} from '../../Models/Endereco/enderecoGet.model';
import { environment } from '../../../assets/environment';

@Injectable({
  providedIn: 'root'
})
export class EnderecoService {
  pathApiUrl:environment;
  constructor(private http:HttpClient){
    this.pathApiUrl = new environment();
   }

  getEnderecos():Observable<EnderecoGet[]>{
    return this.http.get<EnderecoGet[]>(this.pathApiUrl.pathEndereco+"/get").pipe(
      catchError(error => this.handleError(error))
    );
  }

  getEnderecoAPI(cep:string){
      return this.http.get("https://viacep.com.br/ws/"+cep+"/json/").pipe(
        catchError(error => this.handleError(error))
      );
  }

  handleError(error:any) {
    console.error('Ocorreu um erro!', error);
    return throwError(() => error);
  }
  teste(dados:any){

  }
}
