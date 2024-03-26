package com.br.eCormmerce.controllers;

import java.util.List;

import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.service.VendedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {
    
    @Autowired
    private VendedorService vendedorService;

    public List<Vendedor> lsitarVendedor(){
        return vendedorService.lsitarVendedor();
    }
}
