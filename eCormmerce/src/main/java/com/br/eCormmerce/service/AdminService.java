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
import com.br.eCormmerce.repositorys.CategoriaRepository;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.VendedorRepository;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Admin> listarAdmins(){
        return adminRepository.findAll();
    }

    public  ResponseEntity<Object> criarAdmin(Admin admin){
        if (admin != null) {
            if (clienteRepository.existsByCpf(admin.getCpf())) {
                Optional<Cliente> clienteOptional = clienteRepository.findByCpf(admin.getCpf());
                Cliente cliente = clienteOptional.get();
                if (cliente.getNome().equals(admin.getNome())) {
                    return ResponseEntity.ok(adminRepository.save(admin));
                }
                String cpfJaEmUso = "CPF informado já esta em uso";
                return ResponseEntity.badRequest().body(cpfJaEmUso);
            }
            if (vendedorRepository.existsByCpf(admin.getCpf())) {
                Optional<Vendedor> vendedorOptional = vendedorRepository.findByCpf(admin.getCpf());
                Vendedor vendedor = vendedorOptional.get();
                if (vendedor.getNome().equals(admin.getNome())) {
                    return ResponseEntity.ok(adminRepository.save(admin)); 
                }
                String cpfJaEmUso = "CPF informado já esta em uso";
                return ResponseEntity.badRequest().body(cpfJaEmUso);
            }else{
                return ResponseEntity.ok(adminRepository.save(admin));
            }
        }
        String adminNaoCriado = "O admin não pode ser nulo";
        return ResponseEntity.badRequest().body(adminNaoCriado);
    }

    public ResponseEntity<Object> atualizarAdmin(Long id, Admin admin){
        if(adminRepository.existsById(id)){
            admin.setAdmin_id(id);
            return ResponseEntity.ok(adminRepository.save(admin));
        }
        String idAdminNaoEncontrado = "Não foi encontrado uma entidade com esse ID";
        return ResponseEntity.badRequest().body(idAdminNaoEncontrado);
    }

    public ResponseEntity<Object> deletarAdmin(Long id){
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            String adminExcluido = "Admin excluido com sucesso!";
            return ResponseEntity.ok(adminExcluido);
        }
        String idAdminNaoEncontrado = "Id não encontrado";
        return ResponseEntity.badRequest().body(idAdminNaoEncontrado);
    }
}
