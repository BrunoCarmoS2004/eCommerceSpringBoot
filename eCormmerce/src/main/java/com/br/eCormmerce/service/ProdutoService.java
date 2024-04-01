package com.br.eCormmerce.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendedor;
import com.br.eCormmerce.repositorys.CategoriaRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendedorRepository;

@Service
public class ProdutoService {
  @Autowired
  private ProdutosRepository produtosRepository;
  @Autowired 
  private VendedorRepository vendedorRepository;
  @Autowired
  private CategoriaRepository categoriaRepository;

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
    if (vendedorRepository.existsById(produtos.getVendedor_id())) {
      if (categoriaRepository.existsById(produtos.getCategoria_id())) {
        return ResponseEntity.ok(produtosRepository.save(produtos));
      }
      String produtoNaoCriado = "Não existe categoria com esse id";
      return ResponseEntity.badRequest().body(produtoNaoCriado);
    }
    String produtoNaoCriado = "Não existe Vendedor com esse ID";
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

  public List<Produtos> produtosDestaque() {
    List<Produtos> allProdutos = produtosRepository.findAll();
    //Ordenar a lista de produtos com base na quantidade de vendas (em ordem decrescente)
    Collections.sort(allProdutos, Comparator.comparingInt(Produtos::getProduto_qtd_vendas).reversed());
    return allProdutos;
}
}
