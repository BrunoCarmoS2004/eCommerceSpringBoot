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

import com.br.eCormmerce.dto.CategoriaDTO;
import com.br.eCormmerce.dto.EnderecoDTO;
import com.br.eCormmerce.dto.VendasDTO;
import com.br.eCormmerce.models.Endereco;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.models.usuario.Usuario;
import com.br.eCormmerce.service.CategoriaService;
import com.br.eCormmerce.service.EnderecoService;
import com.br.eCormmerce.service.VendasServices;
import com.br.eCormmerce.service.usuarioService.UsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private VendasServices vendasServices;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private UsuarioService usuarioService;
    
    //CRIAR ADMIN
    @GetMapping
    public List<Usuario>listarAdmin(){
        return usuarioService.listarUsuarioAdmin();
    }
    @GetMapping("/clientes")
    public List<Usuario> listarClientes(){
        return usuarioService.listarUsuarioCliente();
    }

    @GetMapping("/vendedores")
    public List<Usuario>listarVendedores(){
        return usuarioService.listarUsuarioVendedor();
    }
    //

    //CRIAR CATEGORIA
    

    @PostMapping("/categoria/criar")
    public ResponseEntity<Object> criarCategoria(@Valid @RequestBody CategoriaDTO categoria){
        return categoriaService.criarCategoria(categoria);
    }

    @PutMapping("/categoria/atualizar/{id}")
    public ResponseEntity<Object> atualizarCategoria(@PathVariable Long id, @Valid @RequestBody CategoriaDTO categoria){
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
    public ResponseEntity<Object>atualizarVenda(@PathVariable Long id, VendasDTO venda){
        return vendasServices.atualizarVendas(id, venda);
    }
    
    @DeleteMapping("/venda/deletar/{id}")
    public ResponseEntity<Object>deletarVenda(@PathVariable Long id){
        return vendasServices.deletarVendas(id);
    }     
    
}
