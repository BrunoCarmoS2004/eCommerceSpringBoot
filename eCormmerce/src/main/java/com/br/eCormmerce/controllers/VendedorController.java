package com.br.eCormmerce.controllers;

import java.util.List;

import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.service.VendedorService;

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

import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {
    
    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public List<Vendedor> listarVendedor(){
        return vendedorService.listarVendedor();
    }
    @PostMapping("/criar")
    public ResponseEntity<Object> criarVendedor(@Valid @RequestBody Vendedor vendedor){
        return vendedorService.criarVendedor(vendedor);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarVendedor(@PathVariable Long id, @Valid @RequestBody Vendedor vendedor){
        return vendedorService.atualizarVendedor(id, vendedor);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarVendedor(@PathVariable Long id){
        return vendedorService.deletarVendedor(id);
    }
}
