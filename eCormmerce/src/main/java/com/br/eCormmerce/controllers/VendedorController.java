package com.br.eCormmerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.dto.ProdutoDTO;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.service.ProdutoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produtos")
    public List<Produtos>listarTodosProdutos(){
        return produtoService.listarTodosProdutos();
    }
    
    @PostMapping("/produto/criar")
    public ResponseEntity<Object>criarProduto(@Valid @RequestBody ProdutoDTO produtos){
        return produtoService.criarProduto(produtos);
    }
    @PutMapping("/produto/atualizar/{id}")
    public ResponseEntity<Object>atualizarProduto(@PathVariable Long id, @Valid @RequestBody ProdutoDTO produtos){
        return produtoService.atualizarProduto(id, produtos);
    }

    @DeleteMapping("/produto/deletar/{id}")
    public ResponseEntity<Object>deletarProduto(@PathVariable Long id){
        return produtoService.deletarProduto(id);
    }

    @GetMapping("/produto/destaques")
    public List<Produtos>produtoDestaque(){
        return produtoService.produtosDestaque();
    }


}
