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

import com.br.eCormmerce.dto.EnderecoDTO;
import com.br.eCormmerce.models.Endereco;
import com.br.eCormmerce.service.EnderecoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;
    @GetMapping("/get")
    public List<Endereco>listarTodosEnderecos(){
        return enderecoService.listarEnderecos();
    }

    @PostMapping("/criar")
    public ResponseEntity<Object> criarEnderecos(@Valid @RequestBody EnderecoDTO categoria){
        return enderecoService.criarEnderecos(categoria);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarEnderecos(@PathVariable Long id, @Valid @RequestBody EnderecoDTO categoria){
        return enderecoService.atualizarEnderecos(id, categoria);
    }

    @DeleteMapping("/deletar/{id}")  
    public ResponseEntity<Object> deletarEnderecos(@PathVariable Long id){
        return enderecoService.deletarEnderecos(id);
    }
}
