package com.br.eCormmerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendedorRepository;
import com.br.eCormmerce.service.VendasServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendas")
public class VendasController {
  @Autowired
  private VendasServices vendasServices;
  private ClienteRepository clienteRepository;
  private VendedorRepository vendedorRepository;
  private ProdutosRepository produtosRepository;

  @PostMapping("/comprar")
  public ResponseEntity<Object>criarVenda(@Valid @RequestBody Vendas venda){
    return vendasServices.criarVendas(venda);
  }
}
