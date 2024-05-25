import { FormControl } from "@angular/forms";

export interface avaliacaoForm{
  avaliaca_titulo:FormControl;
  avaliaca_texto:FormControl;
  avaliaca_estrelas:FormControl;
  avaliaca_imagem:FormControl;
  produtosId:FormControl;
  usuarioId:FormControl;
}
