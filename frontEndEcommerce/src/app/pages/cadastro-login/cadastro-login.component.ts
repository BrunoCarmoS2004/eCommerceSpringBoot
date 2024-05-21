import { CategoriaPost } from './../../Models/Categoria/categoriaPost.model';
import {UsuarioRegisterForm } from '../../Models/Usuarios/usuarioRegisterForm.model';
import { UsuarioRole } from './../../Models/Usuarios/usuarioRole.model';
import { Component, OnInit, ViewEncapsulation } from '@angular/core';
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
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { error } from 'console';

@Component({
  selector: 'app-cadastro-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, FormsModule, MatFormFieldModule , MatInputModule, MatIconModule],
  providers:[
    UsuarioService
  ],
  encapsulation: ViewEncapsulation.None,
  templateUrl: './cadastro-login.component.html',
  styleUrl: './cadastro-login.component.scss'
})
export class CadastroLoginComponent implements OnInit{

  isRegisterNulo:boolean = false;
  isLoginNulo:boolean = false;
  usuarioRole:UsuarioRole;
  isValidPassword:boolean = true;
  isValidUser:boolean = false;
  isValidEmail:boolean = true;
  confirmPassword:string = "";
  isEqualPassoword:boolean = true;
  isMinLenghCpf:boolean = true;
  emailEmUso:boolean = false;
  cpfEmUso:boolean = false;

  enderecoAPI:any  = {};
  paises = new Set<string>();
  estados = new Set<string>();
  enderecos:EnderecoGet[] = [];
  enderecoPais:string[] = [];
  enderecoEstado:string[] = [];
  enderecoCidade:string[] = [];
  selectedPais:any;
  busacandoAPICEP: boolean = true;
  isCampoVazioEndereco: boolean = false;

  cep:string = "";
  complemento:string = "";

  usuarioRegisterForm!:FormGroup;
  usuarioRegistro:UsuarioRegister;

  usuarioLoginForm!:FormGroup<usuarioLoginForm>;
  usuarioLogin:UsuarioLogin;

  categoriaForm!:FormGroup<CategoriaForm>
  categoriaPost:CategoriaPost;
  cepInputSubject = new Subject<string>()
  cpfInputSubject = new Subject<string>()

  constructor(private usuarioService:UsuarioService, public dialog:MatDialog, private categoriaService:CategoriaService, private enderecoService:EnderecoService){
    this.usuarioRole = new UsuarioRole();

    this.usuarioRegistro = new UsuarioRegister(this.usuarioRole.admin);
    this.usuarioLogin = new UsuarioLogin();
    this.categoriaPost = new CategoriaPost();
    this.cepInputSubject.pipe(
      debounceTime(3000)
    ).subscribe(() => {
      this.busacandoAPICEP = true;
      this.getEnderecoViaCepAPI();
    });
    this.cpfInputSubject.pipe(
      debounceTime(500)
    ).subscribe(()=>{
      this.mascaraCPF(this.usuarioRegisterForm.value.cpf);
    })
  }
  ngOnInit(): void {
    this.usuarioRegisterForm = new FormGroup({
      email:new FormControl('',[Validators.requiredTrue, Validators.email]),
      password:new FormControl('',[Validators.requiredTrue, Validators.minLength(8)]),
      confirmPassword:new FormControl('',[Validators.requiredTrue,Validators.minLength(8)]),
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
    const EMAIL_REGEXP = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    this.isEqualPassoword = true;
    this.isValidEmail = true;
    this.isValidPassword= true;
    this.isMinLenghCpf = true;
    this.isRegisterNulo = false;
    Object.keys(this.usuarioRegisterForm.controls).forEach(field => {
      const control = this.usuarioRegisterForm.get(field);
      if (control instanceof FormControl) {
        if (control.value == null || control.value == ''){
          this.isRegisterNulo = true;
        }
      }
    });
    if (this.usuarioRegisterForm.value.email !== '' && (this.usuarioRegisterForm.value.email.length <= 5 || !EMAIL_REGEXP.test(this.usuarioRegisterForm.value.email))) {
      this.isValidEmail = false;
    }
    if(this.usuarioRegisterForm.value.cpf.length < 11){
      this.isMinLenghCpf = false;
    }
    if(this.usuarioRegisterForm.value.password != this.usuarioRegisterForm.value.confirmPassword){
      this.isEqualPassoword = false
    }
    if(this.usuarioRegisterForm.value.password.length <= 8){
      this.isValidPassword = false;
    }

    if(this.isValidEmail){
      this.usuarioService.verificarEmailEmUso(this.usuarioRegisterForm.value.email).subscribe(
        (response)=>{
          if(response.emailEmUso){
            this.emailEmUso = true;
          }else{
            this.emailEmUso = false;
            this.verificacoesFinalizadas();
          }
        },(error)=>{
          alert("Erro na verificação de email")
        }
      );
    }
    if(this.isMinLenghCpf){
      this.usuarioService.verificarCpfEmUso(this.usuarioRegisterForm.value.cpf).subscribe(
        (response)=>{
          if(response.cpfEmUso == false){
            this.cpfEmUso = false;
            this.verificacoesFinalizadas();
          }else{
            this.cpfEmUso = true;

          }
        },(error)=>{
          alert("Erro na verificação de CPF")
        }
      )
    }
  }

  verificacoesFinalizadas(){
    if(!this.isRegisterNulo && this.isEqualPassoword && this.isValidEmail && this.isValidPassword && this.isMinLenghCpf && this.emailEmUso == false && this.cpfEmUso == false){
      this.emailEmUso = false;
      this.cpfEmUso = false;
      this.isValidUser = true;
      this.isMinLenghCpf = true;
      this.isEqualPassoword = true;
      this.isValidEmail = true;
      this.isValidPassword= true;
    }
  }

  isCampoVazioDadosUsuario(campo: FormControl): boolean {
    if(this.isRegisterNulo){
      return campo.value === '' || campo.value === null || campo.value === undefined;
    }
    return false;
  }
  trazerCpf() {
    this.cpfInputSubject.next(this.usuarioRegisterForm.value.cpf);
  }

  mascaraCPF(valor:string){
    const cpf = valor.replace(/\D/g,'')
    const mascaraCPF = cpf.replace(/(\d{3})(\d{3})(\d{3})(\d{2})/, '$1.$2.$3-$4');
    this.usuarioRegisterForm.patchValue({
      cpf: mascaraCPF
    });
  }
  mascaraTelefone(valor: string) {
    const numero = valor.replace(/\D/g, '');
    let mascaraTelefone = numero;

    if (numero.length <= 10) {
      mascaraTelefone = numero.replace(/(\d{2})(\d{4})(\d{4})/, '($1) $2-$3');
    } else {
      mascaraTelefone = numero.replace(/(\d{2})(\d{5})(\d{4})/, '($1) $2-$3');
    }
    this.usuarioRegisterForm.patchValue({
      numeroTelefone: mascaraTelefone
    });
  }

  registarUsuario(){
    if(this.isRegisterNulo == false){
      this.usuarioRegistro.email = this.usuarioRegisterForm.value.email;
      this.usuarioRegistro.password = this.usuarioRegisterForm.value.password;
      this.usuarioRegistro.nome = this.usuarioRegisterForm.value.nome;
      this.usuarioRegistro.sobreNome = this.usuarioRegisterForm.value.sobreNome;
      this.usuarioRegistro.numeroTelefone = this.usuarioRegisterForm.value.numeroTelefone;
      this.usuarioRegistro.cpf = this.usuarioRegisterForm.value.cpf;
      this.usuarioRegistro.cep = this.cep;
      this.usuarioRegistro.logradouro = this.enderecoAPI.logradouro;
      this.usuarioRegistro.complemento = this.complemento;
      this.usuarioRegistro.bairro = this.enderecoAPI.bairro;
      this.usuarioRegistro.localidade = this.enderecoAPI.localidade;
      this.usuarioRegistro.uf = this.enderecoAPI.uf;
      this.usuarioRegistro.pais = this.selectedPais;
      this.isCampoVazioEndereco = false;
    }
    this.usuarioService.registro(this.usuarioRegistro).subscribe(
      () => {
        alert("Cadastro realizado com sucesso!");
      },
      (error) => {
        alert("Erro ao cadastrar usuário. Por favor, tente novamente.");
      }
    );
  }


  login(){
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
      this.usuarioService.login(this.usuarioLogin).subscribe(
        () =>{
          alert("Login Realizado com sucesso!")
          setTimeout(() => {
          window.location.reload();
          }, 1000);
        },(error)=>{
          alert("Erro ao fazer o login. Por favor, tente novamente.");
        }
      );
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

  trazerDadosViaCep() {
    this.busacandoAPICEP = false;
    this.cepInputSubject.next(this.cep);
  }

  getEnderecoViaCepAPI(){
    this.enderecoService.getEnderecoAPI(this.cep).subscribe(
      (data) =>   {
      this.enderecoAPI = data;
    },
    (error) => {
      if (error.status === 400) {
        this.abrirModal();
      } else {
        this.abrirModal();
        console.error('Erro ao obter o endereço via CEP:', error);
      }
    });
  }

  isCampoVazioDadosEndereco(){
    const { localidade, logradouro, bairro, uf } = this.enderecoAPI;
      if (!localidade || !logradouro || !bairro || !uf) {
        this.isCampoVazioEndereco = true;
        console.log(this.isCampoVazioEndereco)
      }else {
        this.registarUsuario()
      }
  }

  voltar(){
    this.isValidUser = false;
  }
}
