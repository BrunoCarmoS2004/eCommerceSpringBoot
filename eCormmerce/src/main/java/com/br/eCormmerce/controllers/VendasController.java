package com.br.eCormmerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.service.CarrinhoService;
import com.br.eCormmerce.service.VendasServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class VendasController {
  @Autowired
  private VendasServices vendasServices;
  @Autowired
  private CarrinhoService carrinhoService;

  @GetMapping("/vendedorDestaque")
  public ResponseEntity<Object> vendedorDestaque(){
    return vendasServices.vendedorDestaque();
  }
  //CARRINHO
  @PatchMapping("/{produto_id}")
  public ResponseEntity<Object> adicionarProdutoAoCarrinho(@PathVariable Long produto_id, @RequestBody Cliente cliente){
      return carrinhoService.adicionarProdutoCarrinho(produto_id, cliente);
  }
}
