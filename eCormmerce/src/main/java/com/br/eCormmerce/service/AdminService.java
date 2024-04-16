package com.br.eCormmerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Admin;
import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Endereco;
import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.repositorys.AdminRepository;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.EnderecoRespository;
import com.br.eCormmerce.repositorys.VendedorRepository;

@Service
public class AdminService implements PessoaService<Admin>{
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired
    private EnderecoRespository enderecoRespository;
    @Override
    public List<Admin> listarUsuario(){
        return adminRepository.findAll();
    }
    
    @Override
    public  ResponseEntity<Object> criarUsuario(Admin admin){
        if (enderecoRespository.existsById(admin.getEnderecoId())) {
            Optional<Endereco> enderecoOptional = enderecoRespository.findById(admin.getEnderecoId());
            Endereco endereco = enderecoOptional.get();
            if (clienteRepository.existsByCpf(admin.getCpf())) {
                Optional<Cliente> clienteOptional = clienteRepository.findByCpf(admin.getCpf());
                Cliente cliente = clienteOptional.get();
                if (cliente.getNome().equals(admin.getNome())) {
                    admin.getEnderecos().add(endereco);
                    return ResponseEntity.ok(adminRepository.save(admin));
                }
                String cpfJaEmUso = "CPF informado já esta em uso";
                return ResponseEntity.badRequest().body(cpfJaEmUso);
            }
            if (vendedorRepository.existsByCpf(admin.getCpf())) {
                Optional<Vendedor> vendedorOptional = vendedorRepository.findByCpf(admin.getCpf());
                Vendedor vendedor = vendedorOptional.get();
                if (vendedor.getNome().equals(admin.getNome())) {
                    admin.getEnderecos().add(endereco);
                    return ResponseEntity.ok(adminRepository.save(admin)); 
                }
                String cpfJaEmUso = "CPF informado já esta em uso";
                return ResponseEntity.badRequest().body(cpfJaEmUso);
            }else{
                admin.getEnderecos().add(endereco);
                return ResponseEntity.ok(adminRepository.save(admin));
            }
        }
        String enderecoNaoEncontrado = "Endereço nao encontrado";
        return ResponseEntity.badRequest().body(enderecoNaoEncontrado);
    }

    @Override
    public ResponseEntity<Object> atualizarUsuario(Long id, Admin admin){
        if(adminRepository.existsById(id)){
            admin.setAdmin_id(id);
            return ResponseEntity.ok(adminRepository.save(admin));
        }
        String idAdminNaoEncontrado = "Não foi encontrado uma entidade com esse ID";
        return ResponseEntity.badRequest().body(idAdminNaoEncontrado);
    }

    @Override
    public ResponseEntity<Object> deletarUsuario(Long id){
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            String adminExcluido = "Admin excluido com sucesso!";
            return ResponseEntity.ok(adminExcluido);
        }
        String idAdminNaoEncontrado = "Id não encontrado";
        return ResponseEntity.badRequest().body(idAdminNaoEncontrado);
    }
}
