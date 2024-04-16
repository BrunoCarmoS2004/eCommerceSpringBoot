package com.br.eCormmerce.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Carrinho;
import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.repositorys.CarrinhoRepository;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendasRepository;

@Service
public class CarrinhoService {
  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private CarrinhoRepository carrinhoRepository;
  @Autowired
  private ProdutosRepository produtosRepository;
  @Autowired
  private VendasRepository vendasRepository;

  public ResponseEntity<Object> listarCarrinho(Cliente cliente_id){
    if (clienteRepository.existsById(cliente_id.getId())) {
      Optional<Cliente> clienteOptional = clienteRepository.findById(cliente_id.getId());
      Cliente cliente = clienteOptional.get();
      return ResponseEntity.ok(carrinhoRepository.findById(cliente.getCarrinho().getCarrinho_id()));
    }
    String clienteNaoEncontrado = "Cliente não encontrado!";
    return ResponseEntity.badRequest().body(clienteNaoEncontrado);
  }
  public ResponseEntity<Object> adicionarProdutoCarrinho(Long produto_id, Cliente cliente_id){
    if (clienteRepository.existsById(cliente_id.getId())) {
        if (produtosRepository.existsById(produto_id)) {
            Optional<Cliente> clienteOptional = clienteRepository.findById(cliente_id.getId());
            Cliente cliente = clienteOptional.get();
            Optional<Produtos> produtoOptional = produtosRepository.findById(produto_id);
            Produtos produtos = produtoOptional.get();
            Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(cliente.getCarrinho().getCarrinho_id());
            if (carrinhoOptional.isPresent()) {
              Carrinho carrinho = carrinhoOptional.get();
              carrinho.getProduto().add(produtos);
              carrinho.setTotal(carrinho.getTotal() + produtos.getProduto_preco());
              carrinho.setCarrinho_id(cliente.getCarrinho().getCarrinho_id());
              carrinhoRepository.save(carrinho);
              String produtoAdicionado = produtos.getProduto_titulo()+" foi adicionado com sucesso ao carrinho!";
              return ResponseEntity.ok(produtoAdicionado);
            }
        }
        String produtoNaoEncontrado = "Produto não encontrado!";
        return ResponseEntity.badRequest().body(produtoNaoEncontrado);
    }
    String clienteNaoEncontrado = "Cliente não encontrado!";
    return ResponseEntity.badRequest().body(clienteNaoEncontrado);
  }

  public ResponseEntity<Object> removerProdutosCarrinho(Long produto_id,  Cliente cliente_id){
    if (clienteRepository.existsById(cliente_id.getId())) {
      if (produtosRepository.existsById(produto_id)) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente_id.getId());
        Cliente cliente = clienteOptional.get();
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(cliente.getCarrinho().getCarrinho_id());
        Carrinho carrinho = carrinhoOptional.get();
        Produtos excluir = carrinho.getProduto().stream().filter(produto -> produto.getProduto_id().equals(produto_id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        carrinho.getProduto().remove(excluir);
        carrinho.setCarrinho_id(cliente.getCarrinho().getCarrinho_id());
        carrinhoRepository.save(carrinho);
      }
      String produtoNaoEncontrado = "Produto não encontrado!";
      return ResponseEntity.badRequest().body(produtoNaoEncontrado);
    }
    String clienteNaoEncontrado = "Cliente não encontrado!";
    return ResponseEntity.badRequest().body(clienteNaoEncontrado);
  }

  public ResponseEntity<Object>comprarTodosItens(Cliente cliente_id){
    if (clienteRepository.existsById(cliente_id.getId())) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente_id.getId());
        Cliente cliente = clienteOptional.get();
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(cliente.getCarrinho().getCarrinho_id());
        Carrinho carrinho = carrinhoOptional.get();
        if (cliente.getSaldo() >= carrinho.getTotal()) {
          for (Produtos produtos : carrinho.getProduto()) {
            cliente.setSaldo(cliente.getSaldo() - produtos.getProduto_preco());
            Vendas vendas = new Vendas(produtos.getVendedorId(), produtos.getProduto_id(), cliente.getId());
            vendasRepository.save(vendas);
          }
          carrinho.setCarrinho_id(cliente.getCarrinho().getCarrinho_id());
          carrinho.getProduto().clear();
          carrinhoRepository.save(carrinho);
          String itensComprados = "Itens comprados com sucesso!";
          return ResponseEntity.ok(itensComprados);
        }
        String clienteSemSaldo = "Cliente sem saldo suficiente, retire algum item, ou adicione saldo";
        return ResponseEntity.badRequest().body(clienteSemSaldo);
    }
    String clienteNaoEncontrado = "Cliente não encontrado!";
    return ResponseEntity.badRequest().body(clienteNaoEncontrado);
  }

  public ResponseEntity<Object> comprarUmItem(Long produto_id, Cliente cliente_id){
    if (clienteRepository.existsById(cliente_id.getId())) {
      if (produtosRepository.existsById(produto_id)) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(cliente_id.getId());
        Cliente cliente = clienteOptional.get();
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(cliente.getCarrinho().getCarrinho_id());
        Carrinho carrinho = carrinhoOptional.get();
        Produtos produtoSelecionado = carrinho.getProduto().stream().filter(produto -> produto.getProduto_id().equals(produto_id)).findFirst().orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        if (cliente.getSaldo() >= produtoSelecionado.getProduto_preco()) {
          cliente.setSaldo(cliente.getSaldo() - produtoSelecionado.getProduto_preco());
          Vendas vendas = new Vendas(produtoSelecionado.getVendedorId(), produtoSelecionado.getProduto_id(), cliente.getId());
          vendasRepository.save(vendas);
          carrinho.getProduto().remove(produtoSelecionado);
          carrinho.setCarrinho_id(cliente.getCarrinho().getCarrinho_id());
          carrinhoRepository.save(carrinho);
          String itemComprado = produtoSelecionado.getProduto_titulo()+" comprado com sucesso!";
          return ResponseEntity.ok(itemComprado);
        }
        String clienteSemSaldo = "Cliente sem saldo suficiente, adicione saldo para comprar "+produtoSelecionado.getProduto_titulo();
        return ResponseEntity.badRequest().body(clienteSemSaldo);
      }
      String produtoNaoEncontrado = "Produto não encontrado!";
      return ResponseEntity.badRequest().body(produtoNaoEncontrado);
    }
    String clienteNaoEncontrado = "Cliente não encontrado!";
    return ResponseEntity.badRequest().body(clienteNaoEncontrado);
  }
}
