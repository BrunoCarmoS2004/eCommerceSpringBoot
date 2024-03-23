package com.br.eCormmerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.repositorys.ProdutosRepository;

@Service
public class ProdutoService {
  @Autowired
  private ProdutosRepository produtosRepository;

  public List<Produtos>listarTodosProdutos(){
    return produtosRepository.findAll();
  }

  public List<Produtos>listarProdutoPorVendedor(Long id){
    List<Produtos> produtos = produtosRepository.findByVendedorId(id);  
    return produtos;
  }

  public List<Produtos>listarProdutoPorCategoria(Long id){
    List<Produtos> produtos = produtosRepository.findByCategoriaId(id);  
    return produtos;
  }

  public ResponseEntity<Object>criarProduto(Produtos produtos){
    if (produtos != null) {
      return ResponseEntity.ok(produtosRepository.save(produtos));
    }
    String produtoNaoCriado = "O produto não pode ser nulo";
    return ResponseEntity.badRequest().body(produtoNaoCriado);
  }

  public ResponseEntity<Object>atualizarProduto(Long id, Produtos produtos){
    if (produtosRepository.existsById(id)) {
      produtos.setProduto_id(id);
      return ResponseEntity.ok(produtosRepository.save(produtos));
    }
    String idProdutoNaoEncontrado = "O id do produto não foi encontrado!";
    return ResponseEntity.badRequest().body(idProdutoNaoEncontrado);
  }

  public ResponseEntity<Object>deletarProduto(Long id){
    if(produtosRepository.existsById(id)){
      produtosRepository.deleteById(id);
      String produtoExcluido = "O produto foi excluido com sucesso!";
      return ResponseEntity.ok(produtoExcluido);
    }
    String idProdutoNaoEncontrado = "Não existe produto com esse Id!";
    return ResponseEntity.badRequest().body(idProdutoNaoEncontrado);
  }
}
