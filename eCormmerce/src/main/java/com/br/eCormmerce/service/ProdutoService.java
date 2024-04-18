package com.br.eCormmerce.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.dto.ProdutoDTO;
import com.br.eCormmerce.models.Categoria;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.repositorys.CategoriaRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.usuarioRepository.UsuarioRepository;

@Service
public class ProdutoService {
  @Autowired
  private ProdutosRepository produtosRepository;
  @Autowired 
  private UsuarioRepository usuarioRepository;
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

  public ResponseEntity<Object>criarProduto(ProdutoDTO produtoDTO){
    if (usuarioRepository.existsById(produtoDTO.vendedorId())) {
      if (categoriaRepository.existsById(produtoDTO.categoriaId())){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(produtoDTO.categoriaId());
        Categoria categoria = categoriaOptional.get();
        Produtos produtos = new Produtos(produtoDTO.produto_titulo(),produtoDTO.produto_preco(),produtoDTO.produto_quantidade(),produtoDTO.produto_descricao(),produtoDTO.produto_imagem(),produtoDTO.vendedorId(),produtoDTO.categoriaId());
        produtos.setCategoriaNome(categoria.getCategoria_nome());
        return ResponseEntity.ok(produtosRepository.save(produtos));
      }
      String produtoNaoCriado = "Não existe categoria com esse id";
      return ResponseEntity.badRequest().body(produtoNaoCriado);
    }
    String produtoNaoCriado = "Não existe Vendedor com esse ID";
    return ResponseEntity.badRequest().body(produtoNaoCriado);
  }

  public ResponseEntity<Object>atualizarProduto(Long id, ProdutoDTO produtoDTO){
    if (produtosRepository.existsById(id)) {
      if (categoriaRepository.existsById(produtoDTO.categoriaId())) {
        Produtos produtos = new Produtos();
        produtos.setCategoriaId(produtoDTO.categoriaId());
        produtos.setProduto_descricao(produtoDTO.produto_descricao());
        produtos.setProduto_imagem(produtoDTO.produto_imagem());
        produtos.setProduto_preco(produtoDTO.produto_preco());
        produtos.setProduto_quantidade(produtoDTO.produto_quantidade());
        produtos.setProduto_titulo(produtoDTO.produto_titulo());
        produtos.setVendedorId(produtoDTO.vendedorId());
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(produtoDTO.categoriaId());
        Categoria categoria = categoriaOptional.get();
        produtos.setCategoriaNome(categoria.getCategoria_nome());
        return ResponseEntity.ok(produtosRepository.save(produtos));
      }
      String produtoNaoCriado = "Não existe categoria com esse id";
      return ResponseEntity.badRequest().body(produtoNaoCriado);
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
