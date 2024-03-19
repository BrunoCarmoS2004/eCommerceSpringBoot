package com.br.eCormmerce.service;

import java.util.List;

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.repositorys.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Retry;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.net.server.Client;

@Service
public class ClienteService {
    
    @Autowired
    ClienteRepository clienteRepository;

    public List<Cliente> listarClientes(){
        return clienteRepository.findAll();
    }

    public Cliente criarCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente cliente){
        if(clienteRepository.existsById(id)){
            cliente.setCliente_id(id);
            return clienteRepository.save(cliente);
        }
        return null;
    }

    public boolean deletarCliente(Long id){
        if(clienteRepository.existsById(id)){
            clienteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public int qntCliente(){
        return clienteRepository.findAll().size();
    }
}
