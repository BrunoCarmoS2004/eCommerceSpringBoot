package com.br.eCormmerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.models.Categoria;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.service.BuscaService;
import com.br.eCormmerce.service.CategoriaService;
@RestController
@RequestMapping("/buscar")
public class BuscaController {
    @Autowired 
    private BuscaService buscaService;
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/produto/{produto_busca}")
    public List<Produtos> buscarProdutos(@PathVariable String produto_busca){
        return buscaService.buscarProdutos(produto_busca);
    }
    @GetMapping("/categorias")
    public List<Categoria>listarTodasCategorias(){
        return categoriaService.listarCategoria();
    }
}
