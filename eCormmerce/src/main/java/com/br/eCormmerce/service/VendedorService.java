package com.br.eCormmerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Admin;
import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.repositorys.AdminRepository;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendedorRepository;

@Service
public class VendedorService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Vendedor>listarVendedores(){
        return vendedorRepository.findAll();
    } 

    public ResponseEntity<Object>criarVendedor(Vendedor vendedor){
        if (vendedor != null) {
            if (adminRepository.existsByCpf(vendedor.getCpf())) {
                Optional<Admin> adminOptional = adminRepository.findByCpf(vendedor.getCpf());
                Admin admin = adminOptional.get();
                if (admin.getNome().equals(vendedor.getNome())) {
                    return ResponseEntity.ok(vendedorRepository.save(vendedor));
                }
                String cpfJaEmUso = "CPF informado já esta em uso";
                return ResponseEntity.badRequest().body(cpfJaEmUso);
            }
            if (clienteRepository.existsByCpf(vendedor.getCpf())) {
                Optional<Cliente> clienteOptional = clienteRepository.findByCpf(vendedor.getCpf());
                Cliente cliente = clienteOptional.get();
                if (cliente.getNome().equals(vendedor.getNome())) {
                    return ResponseEntity.ok(vendedorRepository.save(vendedor)); 
                }
                String cpfJaEmUso = "CPF informado já esta em uso";
                return ResponseEntity.badRequest().body(cpfJaEmUso);
            }else{
                return ResponseEntity.ok(vendedorRepository.save(vendedor)); 
            }
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
