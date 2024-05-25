import { avaliacaoGet } from "../Avaliacao/avaliacaoGet.model";
import { carrinhoGet } from "../Carrinho/carrinhoGet.model";
import { CategoriaGet } from "../Categoria/categoriaGet.model";
import { produtoGet } from "../Produto/produtoGet.model";
import { vendaGet } from "../Vendas/vendaGet.model";

export interface usuarioGet{
  email:string;
  password:string;
  nome:string;
  sobreNome:string;
  numeroTelefone:string;
  cpf:string;
  role:string;
  cep:string;
  logradouro:string;
  complemento:string;
  bairro:string;
  localidade:string;
  uf:string;
  pais:string;
  saldo:number;
  carrinho:carrinhoGet[];
  compras:vendaGet[];
  avaliacoes:avaliacaoGet[];
  vendas:vendaGet[];
  produtos:produtoGet[];
  categorias:CategoriaGet[];
}
