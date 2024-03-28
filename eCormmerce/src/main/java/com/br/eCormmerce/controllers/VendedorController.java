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

import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.service.ProdutoService;
import com.br.eCormmerce.service.VendedorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {
    @Autowired
    private VendedorService vendedorService;
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public List<Vendedor>listarVendedores(){
        return vendedorService.listarUsuario();
    }
    @PostMapping("/criar")
    public ResponseEntity<Object>criarVendedor(@Valid @RequestBody Vendedor vendedor){
        return vendedorService.criarUsuario(vendedor);
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object>atualizarVendedor(@PathVariable Long id, @Valid @RequestBody Vendedor vendedor){
        return vendedorService.atualizarUsuario(id, vendedor);
    }
    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Object>deletarVendedor(@PathVariable Long id){
        return vendedorService.deletarUsuario(id);
    }

    //CRUD PRODUTO

    @GetMapping("/produtos")
    public List<Produtos>listarTodosProdutos(){
        return produtoService.listarTodosProdutos();
    }
    
    @GetMapping("/produtos/{id}")
    public List<Produtos>listarProdutosVendedor(@PathVariable Long id){
        return produtoService.listarProdutoPorVendedor(id);
    }

    @PostMapping("/produto/criar")
    public ResponseEntity<Object>criarProduto(@Valid @RequestBody Produtos produtos){
        return produtoService.criarProduto(produtos);
    }
    @PutMapping("/produto/atualizar/{id}")
    public ResponseEntity<Object>atualizarProduto(@PathVariable Long id, @Valid @RequestBody Produtos produtos){
        return produtoService.atualizarProduto(id, produtos);
    }

    @DeleteMapping("/produto/deletar/{id}")
    public ResponseEntity<Object>deletarProduto(@PathVariable Long id){
        return produtoService.deletarProduto(id);
    }


}
