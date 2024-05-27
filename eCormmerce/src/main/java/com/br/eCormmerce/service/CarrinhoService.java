package com.br.eCormmerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Carrinho;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.models.usuario.Usuario;
import com.br.eCormmerce.repositorys.CarrinhoRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendasRepository;
import com.br.eCormmerce.repositorys.usuarioRepository.UsuarioRepository;

@Service
public class CarrinhoService {
  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private CarrinhoRepository carrinhoRepository;
  @Autowired
  private ProdutosRepository produtosRepository;
  @Autowired
  private VendasRepository vendasRepository;
  
  public ResponseEntity<Object> listarCarrinho(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserDetails usuarioDetails = usuarioRepository.findByEmail(userDetails.getUsername());
    Usuario usuario = (Usuario) usuarioDetails;
    return ResponseEntity.ok(carrinhoRepository.findById(usuario.getCarrinho().getCarrinho_id()));
  }
  
  public ResponseEntity<Object> adicionarProdutoCarrinho(Long produto_id){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserDetails usuarioDetails = usuarioRepository.findByEmail(userDetails.getUsername());
    Usuario usuario = (Usuario) usuarioDetails;
    if (!produtosRepository.existsById(produto_id)){
      String produtoNaoEncontrado = "Produto não encontrado!";
      return ResponseEntity.badRequest().body(produtoNaoEncontrado);
    }
    Optional<Produtos> produtoOptional = produtosRepository.findById(produto_id);
    Produtos produtos = produtoOptional.get();
    Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(usuario.getCarrinho().getCarrinho_id());
    Carrinho carrinho = carrinhoOptional.get();
    carrinho.getProduto().add(produtos);
    carrinho.setTotal(carrinho.getTotal() + produtos.getProduto_preco());
    carrinho.setCarrinho_id(usuario.getCarrinho().getCarrinho_id());
    carrinhoRepository.save(carrinho);
    String produtoAdicionado = produtos.getProduto_titulo()+" foi adicionado com sucesso ao carrinho!";
    return ResponseEntity.ok(produtoAdicionado);
  }

  public ResponseEntity<Object> removerProdutosCarrinho(Long produto_id){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserDetails usuarioDetails = usuarioRepository.findByEmail(userDetails.getUsername());
    Usuario usuario = (Usuario) usuarioDetails;
    if (!produtosRepository.existsById(produto_id)){
      String produtoNaoEncontrado = "Produto não encontrado!";
      return ResponseEntity.badRequest().body(produtoNaoEncontrado);
    }
    Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(usuario.getCarrinho().getCarrinho_id());
    Carrinho carrinho = carrinhoOptional.get();
    Produtos excluir = carrinho.getProduto().stream().filter(produto -> produto.getProduto_id().equals(produto_id)).findFirst().orElseThrow();
    carrinho.getProduto().remove(excluir);
    carrinho.setCarrinho_id(usuario.getCarrinho().getCarrinho_id());
    carrinhoRepository.save(carrinho);
    String produtoRemovido = excluir.getProduto_titulo()+" foi removido com sucesso ao carrinho!";
    return ResponseEntity.ok(produtoRemovido);
  }

  public ResponseEntity<Object>comprarTodosItens(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserDetails usuarioDetails = usuarioRepository.findByEmail(userDetails.getUsername());
    Usuario usuario = (Usuario) usuarioDetails;
    Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(usuario.getCarrinho().getCarrinho_id());
    Carrinho carrinho = carrinhoOptional.get(); 
    if (usuario.getSaldo() >= carrinho.getTotal()){
      carrinho.getProduto().stream().forEach(produto -> {
      usuario.setSaldo(usuario.getSaldo() - produto.getProduto_preco());
      Vendas vendas = new Vendas(produto.getVendedorId(), produto.getProduto_id(), usuario.getId());
      vendasRepository.save(vendas);
      });
      carrinho.setCarrinho_id(usuario.getCarrinho().getCarrinho_id());
      carrinho.getProduto().clear();
      carrinhoRepository.save(carrinho);
      String itensComprados = "Itens comprados com sucesso!";
      return ResponseEntity.ok(itensComprados);
    }
    String clienteSemSaldo = "Sem saldo suficiente, retire algum item, ou adicione saldo";
    return ResponseEntity.badRequest().body(clienteSemSaldo);
  }

  public ResponseEntity<Object> comprarUmItem(Long produto_id){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserDetails usuarioDetails = usuarioRepository.findByEmail(userDetails.getUsername());
    Usuario usuario = (Usuario) usuarioDetails;
    if (!produtosRepository.existsById(produto_id)){
      String produtoNaoEncontrado = "Produto não encontrado!";
      return ResponseEntity.badRequest().body(produtoNaoEncontrado);
    }
    Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(usuario.getCarrinho().getCarrinho_id());
    Carrinho carrinho = carrinhoOptional.get();
    Produtos produtoSelecionado = carrinho.getProduto().stream().filter(produto -> produto.getProduto_id().equals(produto_id)).findFirst().orElseThrow();
    if (usuario.getSaldo() <= produtoSelecionado.getProduto_preco()) {
      String clienteSemSaldo = "Cliente sem saldo suficiente, adicione saldo para comprar "+produtoSelecionado.getProduto_titulo();
      return ResponseEntity.badRequest().body(clienteSemSaldo);
    }
    usuario.setSaldo(usuario.getSaldo() - produtoSelecionado.getProduto_preco());
    Vendas vendas = new Vendas(produtoSelecionado.getVendedorId(), produtoSelecionado.getProduto_id(), usuario.getId());
    vendasRepository.save(vendas);
    carrinho.getProduto().remove(produtoSelecionado);
    carrinho.setCarrinho_id(usuario.getCarrinho().getCarrinho_id());
    carrinhoRepository.save(carrinho);
    String itemComprado = produtoSelecionado.getProduto_titulo()+" comprado com sucesso!";
    return ResponseEntity.ok(itemComprado);
  }
}
