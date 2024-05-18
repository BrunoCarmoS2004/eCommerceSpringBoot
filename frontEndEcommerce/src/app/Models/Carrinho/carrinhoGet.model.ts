import { produtoGet } from "../Produto/produtoGet.model";
import { usuarioGet } from "../Usuarios/usuarioGet.model";

export interface carrinhoGet{
  carrinho_id:number;
  total:number;
  usuario:usuarioGet;
  produtos:produtoGet[];
}
