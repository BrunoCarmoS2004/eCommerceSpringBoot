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

import com.br.eCormmerce.models.Admin;
import com.br.eCormmerce.models.Categoria;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.repositorys.CategoriaRepository;
import com.br.eCormmerce.service.AdminService;
import com.br.eCormmerce.service.CategoriaService;
import com.br.eCormmerce.service.VendasServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private VendasServices vendasServices;
    
    //CRIAR ADMIN
    @GetMapping
    public List<Admin>listarAdmin(){
        return adminService.listarUsuario();
    }
    @PostMapping("/criar")
    public ResponseEntity<Object>criarAdmin(@Valid @RequestBody Admin admin){
        return adminService.criarUsuario(admin);
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object>atualizarAdmin(@PathVariable Long id, @Valid Admin admin){
        return adminService.atualizarUsuario(id, admin);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object>deletarAdmin(@PathVariable Long id){
        return adminService.deletarUsuario(id);
    }

    //CRIAR CATEGORIA
    @GetMapping("/categorias")
    public List<Categoria>listarTodasCategorias(){
        return categoriaService.listarCategoria();
    }

    @GetMapping("/categorias/{id}")
    public List<Categoria>listarCategoriaPorIdAdmin(@PathVariable Long id){
        return categoriaService.listarCategoriaPorAdmin(id);
    }

    @PostMapping("/categoria/criar")
    public ResponseEntity<Object> criarCategoria(@Valid @RequestBody Categoria categoria){
        return categoriaService.criarCategoria(categoria);
    }

    @PutMapping("/categoria/atualizar/{id}")
    public ResponseEntity<Object> atualizarCategoria(@PathVariable Long id, @Valid @RequestBody Categoria categoria){
        return categoriaService.atualizarCategoria(id, categoria);
    }

    @DeleteMapping("/categoria/deletar/{id}")  
    public ResponseEntity<Object> deletarCategoria(@PathVariable Long id){
        return categoriaService.deletarCategoria(id);
    }

    //Vendas
    @GetMapping("/vendas")
    public List<Vendas>listarVendas(){
        return vendasServices.listarVendas();
    }

    @PutMapping("/venda/atualizar/{id}")
    public ResponseEntity<Object>atualizarVenda(@PathVariable Long id, Vendas venda){
        return vendasServices.criarVendas(venda);
    }
    
    @DeleteMapping("/venda/deletar/{id}")
    public ResponseEntity<Object>deletarVenda(@PathVariable Long id){
        return vendasServices.deletarVendas(id);
    }    

}
