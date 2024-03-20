package com.br.eCormmerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendedorRepository;

@Service
public class VendedorService {
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private ProdutosRepository produtosRepository;

    public List<Vendedor>listarVendedores(){
        return vendedorRepository.findAll();
    } 

    public ResponseEntity<Object>criarVendedor(Vendedor vendedor){
        if (vendedor != null) {
            return ResponseEntity.ok(vendedorRepository.save(vendedor));
        }
        String vendedorNaoCriado = "O Vendedor não pode ser null";
        return ResponseEntity.badRequest().body(vendedorNaoCriado);
    }

    public ResponseEntity<Object>atualizarVendedor(Long id, Vendedor vendedor){
        if (vendedorRepository.existsById(id)) {
            vendedor.setId(id);
            return ResponseEntity.ok(vendedorRepository.save(vendedor));
        }
        String idVendedorNaoEncontrado = "Id do vendedor não foi encontrado";
        return ResponseEntity.badRequest().body(idVendedorNaoEncontrado);
    }

    public ResponseEntity<Object>deletarVendedor(Long id){
        if (vendedorRepository.existsById(id)) {
            vendedorRepository.deleteById(id);
            String vendedorExcluido = "Vendedor excluido com sucesso!";
            return ResponseEntity.ok(vendedorExcluido);
        }
        String vendedorNaoEncontrado = "Id de vendedor não encontrado!";
        return ResponseEntity.badRequest().body(vendedorNaoEncontrado);
    }
}
