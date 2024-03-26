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

import com.br.eCormmerce.models.Avaliacao;
import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.service.AvaliacaoService;
import com.br.eCormmerce.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private AvaliacaoService avaliacaoService;

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.listar();
    }
    @PostMapping("/criar")
    public ResponseEntity<Object> criarCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.criar(cliente);
    }
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        return clienteService.atualizar(id, cliente);
    }
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable Long id){
        return clienteService.deletar(id);
    }

    //AVALIAÇÃO
    @GetMapping("/avaliarproduto")
    public List<Avaliacao> listarAvaliacaos(){
        return avaliacaoService.listarAvaliacoes();
    }
    @PostMapping("/avaliarproduto/criar")
    public ResponseEntity<Object> criarProduto(@Valid @RequestBody Avaliacao avaliacao){
        return avaliacaoService.criarAvaliacao(avaliacao);
    }

    @PutMapping("/avaliarproduto/atualizar/{id}")
    public ResponseEntity<Object> atualizarProduto(@PathVariable Long id, @Valid @RequestBody Avaliacao avaliacao){
        return avaliacaoService.atualizarAvaliacao(id, avaliacao);
    }

    @DeleteMapping("/avaliarproduto/deletar/{id}")
    public ResponseEntity<Object> deletarAvaliacao(@PathVariable Long id){
        return avaliacaoService.deletarAvaliacao(id);
    }
}
