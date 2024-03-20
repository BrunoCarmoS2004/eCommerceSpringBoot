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

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }
    @PostMapping("/criar")
    public ResponseEntity<Object> criarCliente(@RequestBody Cliente cliente){
        return clienteService.criarCliente(cliente);
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        return clienteService.atualizarCliente(id, cliente);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable Long id){
        return clienteService.deletarFuncionario(id);
    }
}
