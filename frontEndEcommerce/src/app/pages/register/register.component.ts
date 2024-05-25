import { CategoriaPost } from './../../Models/Categoria/categoriaPost.model';
import {UsuarioRegisterForm } from '../../Models/Usuarios/usuarioRegisterForm.model';
import { UsuarioRole } from './../../Models/Usuarios/usuarioRole.model';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { UsuarioRegister } from '../../Models/Usuarios/usuarioRegister.model';
import { CommonModule } from '@angular/common';
import { UsuarioService } from '../../Services/Usuario/usuario.service';
import { MatDialog } from '@angular/material/dialog';
import { ErroLoginRegisterComponent } from '../../components/erro-login-register/erro-login-register.component';
import { usuarioLoginForm } from '../../Models/Usuarios/usuarioLoginForm.model';
import { UsuarioLogin } from '../../Models/Usuarios/usuarioLogin.model';
import { CategoriaForm } from '../../Models/Categoria/categoriaForm.model';
import { CategoriaService } from '../../Services/Categoria/categoria.service';
import { EnderecoGet } from '../../Models/Endereco/enderecoGet.model';
import { EnderecoService } from '../../Services/Endereco/endereco.service';
import { debounceTime } from 'rxjs/operators';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, FormsModule],
  providers:[
    UsuarioService
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent implements OnInit{

  isRegisterNulo:boolean = false;
  isLoginNulo:boolean = false;
  usuarioRole:UsuarioRole;
  isValidPassword:string = "";
  isValidUser:boolean = false;
  enderecoAPI:any  = {};
  paises = new Set<string>();
  estados = new Set<string>();
  enderecos:EnderecoGet[] = [];
  enderecoPais:string[] = [];
  enderecoEstado:string[] = [];
  enderecoCidade:string[] = [];
  selectedPais:any;

  cep:string = "";
  complemento:string = "";

  usuarioRegisterForm!:FormGroup;
  usuarioRegistro:UsuarioRegister;

  usuarioLoginForm!:FormGroup<usuarioLoginForm>;
  usuarioLogin:UsuarioLogin;

  categoriaForm!:FormGroup<CategoriaForm>
  categoriaPost:CategoriaPost;
  cepInputSubject = new Subject<string>()

  constructor(private usuarioService:UsuarioService, public dialog:MatDialog, private categoriaService:CategoriaService, private enderecoService:EnderecoService){
    this.usuarioRole = new UsuarioRole();

    this.usuarioRegistro = new UsuarioRegister(this.usuarioRole.admin);
    this.usuarioLogin = new UsuarioLogin();
    this.categoriaPost = new CategoriaPost();
    this.cepInputSubject.pipe(
      debounceTime(3000) // Tempo de espera em milissegundos
    ).subscribe(() => {
      this.getEnderecoAPI();
    });
  }
  ngOnInit(): void {
    this.usuarioRegisterForm = new FormGroup({
      email:new FormControl('',[Validators.requiredTrue, Validators.email]),
      password:new FormControl('',[Validators.requiredTrue, Validators.minLength(8)]),
      nome:new FormControl('', [Validators.requiredTrue]),
      cpf:new FormControl('', [Validators.requiredTrue]),
      sobreNome:new FormControl('', [Validators.requiredTrue]),
      numeroTelefone:new FormControl('', [Validators.requiredTrue]),
    });
    this.usuarioLoginForm = new FormGroup({
      email:new FormControl('',[Validators.required, Validators.email]),
      password:new FormControl('',[Validators.required, Validators.minLength(8)])
    });
    this.categoriaForm = new FormGroup({
      categoria_nome:new FormControl('',[Validators.required])
    });
    this.getEnderecos();
  }
  registarDadosBase(){
    this.isRegisterNulo = false;
    Object.keys(this.usuarioRegisterForm.controls).forEach(field => {
      const control = this.usuarioRegisterForm.get(field);
      if (control instanceof FormControl) {
        if (control.value == null || control.value == '') {
          console.log('Campo vazio ou nulo encontrado: ', field);
          this.isRegisterNulo = true;
        }
      }
    });
    this.isValidPassword = this.usuarioRegisterForm.value.password
    if(this.isValidPassword.length < 8){
      this.abrirModal();
    }
  }

  registarUsuario(){
    if(this.isRegisterNulo == false){
      this.usuarioRegistro.email = this.usuarioRegisterForm.value.email;
      this.usuarioRegistro.password = this.usuarioRegisterForm.value.password;
      this.usuarioRegistro.nome = this.usuarioRegisterForm.value.nome;
      this.usuarioRegistro.sobreNome = this.usuarioRegisterForm.value.sobreNome;
      this.usuarioRegistro.numeroTelefone = this.usuarioRegisterForm.value.numeroTelefone
      this.usuarioRegistro.cpf = this.usuarioRegisterForm.value.cpf;
      this.usuarioRegistro.cep = this.cep;
      this.usuarioRegistro.logradouro = this.enderecoAPI.logradouro;
      this.usuarioRegistro.complemento = this.complemento;
      this.usuarioRegistro.bairro = this.enderecoAPI.bairro;
      this.usuarioRegistro.localidade = this.enderecoAPI.localidade;
      this.usuarioRegistro.uf = this.enderecoAPI.uf;
      this.usuarioRegistro.pais = this.selectedPais;
    }
    this.usuarioService.registro(this.usuarioRegistro);
  }


  login(){
    this.isLoginNulo = false;
    Object.keys(this.usuarioLoginForm.controls).forEach(field => {
      const control = this.usuarioLoginForm.get(field);
      if (control instanceof FormControl) {
        if (control.value == null || control.value == '') {
          console.log('Campo vazio ou nulo encontrado: ', field);
          this.isLoginNulo = true;
        }
      }
    });
    if(this.isLoginNulo == false){
      this.usuarioLogin.email = this.usuarioLoginForm.value.email;
      this.usuarioLogin.password = this.usuarioLoginForm.value.password;
      this.usuarioService.login(this.usuarioLogin)
    }
  }

  criarCategoria(){
    this.categoriaPost.categoria_nome = this.categoriaForm.value.categoria_nome;
    this.categoriaService.criarCategoria(this.categoriaPost)
  }

  getEnderecos(){
    //pegar todos os endereços
    this.enderecoService.getEnderecos().subscribe(enderecos =>{
      this.enderecos = enderecos
    });
    //Mandar apenas os paises
    this.enderecos.forEach(paises=>{
      this.enderecoPais.push(paises.pais)
    })
    this.enderecoPais.forEach(pais=>{
      this.paises.add(pais)
    })
  }

  abrirModal(){
    const dialogRef = this.dialog.open(ErroLoginRegisterComponent,{
      width:"500px",
      height:"300px"
    })
  }
  excluirDados(){
    this.usuarioService.excluirDados();
    this.usuarioService.logout();
  }

  onCepInputChange() {
    this.cepInputSubject.next(this.cep);
  }

  getEnderecoAPI(){
    this.enderecoService.getEnderecoAPI(this.cep).subscribe(
      (data) =>   {
      this.enderecoAPI = data;
    });
  }
}
