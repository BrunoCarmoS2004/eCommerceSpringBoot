package com.br.eCormmerce.service;

import java.util.List;

import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.repositorys.VendedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> lsitarVendedor(){
        return vendedorRepository.findAll();
    }

    public Vendedor criarVendedor(Vendedor vendedor){
        return vendedorRepository.save(vendedor);
    }

    public ResponseEntity<Object> atualizarVendedor(Long id, Vendedor vendedor){
        if(vendedorRepository.existsById(id)){
            vendedor.setId(id);
            return ResponseEntity.ok(vendedorRepository.save(vendedor));
        }
        String idNaoEncontrado = "Id não encontrado";
        return ResponseEntity.badRequest().body(idNaoEncontrado);
    }

    public ResponseEntity<Object> deletarVendedor(Long id, Vendedor vendedor){
        if(vendedorRepository.existsById(id)){
            vendedorRepository.existsById(id);
            String vendedorExcluido = "Vendedor excluido com sucesso!";
            return ResponseEntity.ok(vendedorExcluido);
        }
        String IdNaoEncontrado = "Id não encontrado";
        return ResponseEntity.badRequest().body(IdNaoEncontrado);
    }
}
