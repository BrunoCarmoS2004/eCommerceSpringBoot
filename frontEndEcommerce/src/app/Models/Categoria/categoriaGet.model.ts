import { produtoGet } from "../Produto/produtoGet.model";

export interface CategoriaGet{
  categoria_id:number;
  categoria_nome:string;
  adminId:string;
  produtos:produtoGet[];
}
