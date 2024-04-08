package com.br.eCormmerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.service.BuscaService;
@RestController
@RequestMapping("/buscar")
public class BuscaController {
    @Autowired BuscaService buscaService;

    @GetMapping("/{produto_busca}")
    public List<Produtos> buscarProdutos(@PathVariable String produto_busca){
        return buscaService.buscarProdutos(produto_busca);
    }
}
