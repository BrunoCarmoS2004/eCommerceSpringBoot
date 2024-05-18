import { FormControl } from "@angular/forms";

export interface produtoForm{
  produto_titulo:FormControl;
  produto_preco:FormControl;
  produto_quantidade:FormControl;
  produto_descricao:FormControl;
  produto_imagem:FormControl;
  vendedorId:FormControl;
  categoriaId:FormControl;
  categoriaNome:FormControl;
}
