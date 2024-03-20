package com.br.eCormmerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.repositorys.AvaliacaoRepository;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public ResponseEntity<Object> criarCliente(Cliente cliente){
        if(cliente != null){
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }
        String clienteNaoCriado = "O cliente não pode ser nulo";
        return ResponseEntity.badRequest().body(clienteNaoCriado);
    }

    public ResponseEntity<Object> atualizarCliente(Long id, Cliente cliente){
        if(clienteRepository.existsById(id)){
            cliente.setId(id);
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }
        String idNaoEncontrado = "Id Não encontrado";
        return ResponseEntity.badRequest().body(idNaoEncontrado);
    }

    public ResponseEntity<Object> deletarFuncionario(Long id){
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            String clienteExcluido = "Cliente excluido com sucesso!";
            return ResponseEntity.ok(clienteExcluido);
        }
        String idNaoEncontrado = "Id Não encontrado";
        return ResponseEntity.badRequest().body(idNaoEncontrado);
    }
}
