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

    public ResponseEntity<Object> adicionarProdutoCarrinho(Long produto_id, Long cliente_id){
        if (produtosRepository.existsById(produto_id)) {
            if (clienteRepository.existsById(cliente_id)){
                Optional<Produtos>produtoOptional = produtosRepository.findById(produto_id);
                Produtos produtos = produtoOptional.get();
                Optional<Cliente> clienteOptional = clienteRepository.findById(cliente_id);
                Cliente cliente = clienteOptional.get();
                cliente.setCarrinho(produtos);
                produtos.setClienteId(cliente_id);
                String itemAdicionado = produtos.getProduto_titulo()+" foi adicionado ao carrinho!";
                return ResponseEntity.ok(itemAdicionado);
            }
            String clienteNaoEncontrado = "Não foi encontrado um cliente com esse ID!";
            return ResponseEntity.ok(clienteNaoEncontrado);
        }
        String produtoNaoEncontrado = "Não foi encontrado um produto com esse ID!";
        return ResponseEntity.ok(produtoNaoEncontrado);
    }

}
