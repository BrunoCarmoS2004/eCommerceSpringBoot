package com.br.eCormmerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.service.VendasServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/produto")
public class VendasController {
  @Autowired
  private VendasServices vendasServices;

  @PostMapping("/comprar")
  public ResponseEntity<Object>criarVenda(@Valid @RequestBody Vendas venda){
    return vendasServices.criarVendas(venda);
  }

  @GetMapping("/vendedorDestaque")
  public ResponseEntity<Object> vendedorDestaque(){
    return vendasServices.vendedorDestaque();
  }
  
}
