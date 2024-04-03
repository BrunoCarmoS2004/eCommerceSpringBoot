package com.br.eCormmerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.service.BuscaService;
@RestController
@RequestMapping("/busca")
public class BuscaController {
    @Autowired BuscaService buscaService;

    @PostMapping("/{produto_titulo}")
    public List<Produtos> buscarProdutos(@PathVariable String produto_titulo){
        return buscaService.buscarProdutos(produto_titulo);
    }
}
