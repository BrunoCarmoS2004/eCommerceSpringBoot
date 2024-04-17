package com.br.eCormmerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Admin;
import com.br.eCormmerce.models.Carrinho;
import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Endereco;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.repositorys.AdminRepository;
import com.br.eCormmerce.repositorys.CarrinhoRepository;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.EnderecoRespository;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendedorRepository;

@Service
public class ClienteService implements PessoaService<Cliente>{
    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private VendedorRepository vendedorRepository;
    @Autowired 
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private EnderecoRespository enderecoRespository;

    @Override
    public List<Cliente> listarUsuario(){
        return clienteRepository.findAll();
    }
    @Override
    public ResponseEntity<Object> criarUsuario(Cliente cliente){
        if (enderecoRespository.existsById(cliente.getEnderecoId())) {
            Optional<Endereco> enderecoOptional = enderecoRespository.findById(cliente.getEnderecoId());
            Endereco endereco = enderecoOptional.get();
            if (adminRepository.existsByCpf(cliente.getCpf())) {
                Optional<Admin> adminOptional = adminRepository.findByCpf(cliente.getCpf());
                Admin admin = adminOptional.get();
                if (admin.getNome().equals(cliente.getNome())) {
                    //Carrinho carrinho = new Carrinho(cliente);
                    //carrinhoRepository.save(carrinho);
                    cliente.getEnderecos().add(endereco);
                    return ResponseEntity.ok(clienteRepository.save(cliente));
                }
                String cpfJaEmUso = "CPF informado já esta em uso";
                return ResponseEntity.badRequest().body(cpfJaEmUso);
            }
            if (vendedorRepository.existsByCpf(cliente.getCpf())) {
                Optional<Vendedor> vendedorOptional = vendedorRepository.findByCpf(cliente.getCpf());
                Vendedor vendedor = vendedorOptional.get();
                if (vendedor.getNome().equals(cliente.getNome())) {
                    cliente.getEnderecos().add(endereco);
                    return ResponseEntity.ok(clienteRepository.save(cliente)); 
                }
                String cpfJaEmUso = "CPF informado já esta em uso";
                return ResponseEntity.badRequest().body(cpfJaEmUso);
            }else{
                clienteRepository.save(cliente);
                //Carrinho carrinho = new Carrinho(cliente);
                //carrinhoRepository.save(carrinho);
                cliente.getEnderecos().add(endereco);
                return ResponseEntity.ok(cliente); 
            }
        }
        String enderecoNaoEncontrado = "Endereço nao encontrado";
        return ResponseEntity.badRequest().body(enderecoNaoEncontrado);
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

    public ResponseEntity<Object> adicionarSaldo(Long id, Cliente clienteRequest){
        if (clienteRepository.existsById(id)) {
            Optional<Cliente>clienteOptional = clienteRepository.findById(id);
            Cliente cliente = clienteOptional.get();
            cliente.setSaldo(cliente.getSaldo() + clienteRequest.getSaldo());
            String clienteSaldoAtualizado = "Foi adicionado saldo com sucesso\n"
            +"Novo saldo: "+cliente.getSaldo();
            cliente.setId(id);
            clienteRepository.save(cliente);
            return ResponseEntity.ok(clienteSaldoAtualizado);
        }
        String idClienteNaoEncontrado = "Não existe um cliente com esse Id";
        return ResponseEntity.badRequest().body(idClienteNaoEncontrado);
    }
}
