package com.br.eCormmerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Admin;
import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.repositorys.AdminRepository;
import com.br.eCormmerce.repositorys.AvaliacaoRepository;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendedorRepository;

@Service
public class ClienteService implements PessoaService<Cliente>{
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private VendedorRepository vendedorRepository;

    @Override
    public List<Cliente> listarUsuario(){
        return clienteRepository.findAll();
    }
    @Override
    public ResponseEntity<Object> criarUsuario(Cliente cliente){
        if(cliente != null){
            if (adminRepository.existsByCpf(cliente.getCpf())) {
                Optional<Admin> adminOptional = adminRepository.findByCpf(cliente.getCpf());
                Admin admin = adminOptional.get();
                if (admin.getNome().equals(cliente.getNome())) {
                    return ResponseEntity.ok(clienteRepository.save(cliente));
                }
                String cpfJaEmUso = "CPF informado já esta em uso";
                return ResponseEntity.badRequest().body(cpfJaEmUso);
            }
            if (vendedorRepository.existsByCpf(cliente.getCpf())) {
                Optional<Vendedor> vendedorOptional = vendedorRepository.findByCpf(cliente.getCpf());
                Vendedor vendedor = vendedorOptional.get();
                if (vendedor.getNome().equals(cliente.getNome())) {
                    return ResponseEntity.ok(clienteRepository.save(cliente)); 
                }
                String cpfJaEmUso = "CPF informado já esta em uso";
                return ResponseEntity.badRequest().body(cpfJaEmUso);
            }else{
                return ResponseEntity.ok(clienteRepository.save(cliente)); 
            }
        }
        String clienteNaoCriado = "O cliente não pode ser nulo";
        return ResponseEntity.badRequest().body(clienteNaoCriado);
    }
    @Override
    public ResponseEntity<Object> atualizarUsuario(Long id, Cliente cliente){
        if(clienteRepository.existsById(id)){
            cliente.setId(id);
            return ResponseEntity.ok(clienteRepository.save(cliente));
        }
        String idNaoEncontrado = "Id Não encontrado";
        return ResponseEntity.badRequest().body(idNaoEncontrado);
    }
    @Override
    public ResponseEntity<Object> deletarUsuario(Long id){
        if (clienteRepository.existsById(id)) {
            clienteRepository.deleteById(id);
            String clienteExcluido = "Cliente excluido com sucesso!";
            return ResponseEntity.ok(clienteExcluido);
        }
        String idNaoEncontrado = "Id Não encontrado";
        return ResponseEntity.badRequest().body(idNaoEncontrado);
    }
}
