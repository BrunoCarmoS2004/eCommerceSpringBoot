export class produtoPost{
  produto_titulo:string;
  produto_preco:number;
  produto_quantidade:number;
  produto_descricao:string;
  produto_imagem:string;
  vendedorId:string;
  categoriaId:number;
  categoriaNome:string;
  constructor(){
    this.produto_titulo = "";
    this.produto_preco = 0;
    this.produto_quantidade = 0;
    this.produto_descricao = "";
    this.produto_imagem = "";
    this.vendedorId = "";
    this.categoriaId = 0;
    this.categoriaNome = "";
  }
}
