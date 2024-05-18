import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../assets/environment';
import { CategoriaPost } from '../../Models/Categoria/categoriaPost.model';
import { catchError, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {
  pathApiUrl:environment
  constructor(private http:HttpClient) {
    this.pathApiUrl = new environment();
  }

  criarCategoria(categoriaObj:CategoriaPost){
    this.http.post(this.pathApiUrl.pathAdmin+"/categoria/criar",categoriaObj,this.pathApiUrl.getOptions()).pipe(
      catchError(error => this.handleError(error))
    ).subscribe();
    setTimeout(() => {
      window.location.reload();
    }, 1000);
    alert("Criada")
  }

  handleError(error:any) {
    console.error('Ocorreu um erro!', error);
    return throwError(() => error);
  }
}
