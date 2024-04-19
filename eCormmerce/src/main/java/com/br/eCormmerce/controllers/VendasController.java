package com.br.eCormmerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.service.CarrinhoService;
import com.br.eCormmerce.service.ProdutoService;
import com.br.eCormmerce.service.VendasServices;

@RestController
@RequestMapping("/produto")
public class VendasController {
  @Autowired
  private VendasServices vendasServices;
  @Autowired
  private CarrinhoService carrinhoService;
  @Autowired
  private ProdutoService produtoService;

  @GetMapping
  public List<Produtos>listarTodosProdutos(){
        return produtoService.listarTodosProdutos();
  }

  @GetMapping("/vendedorDestaque")
  public ResponseEntity<Object> vendedorDestaque(){
    return vendasServices.vendedorDestaque();
  }
  //CARRINHO
  @PatchMapping("/adicionar-ao-carrinho/{produto_id}")
  public ResponseEntity<Object> adicionarProdutoAoCarrinho(@PathVariable Long produto_id){
      return carrinhoService.adicionarProdutoCarrinho(produto_id);
  }
}
