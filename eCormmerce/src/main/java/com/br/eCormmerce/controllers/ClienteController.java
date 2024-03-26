package com.br.eCormmerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.service.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }
}
