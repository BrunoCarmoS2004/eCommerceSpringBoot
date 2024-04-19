package com.br.eCormmerce.dto;

public record ProdutoDTO(String produto_titulo, double produto_preco, int produto_quantidade, String produto_descricao,
String produto_imagem, Long categoriaId) {
  
}
