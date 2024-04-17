package com.br.eCormmerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Avaliacao;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.repositorys.AvaliacaoRepository;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;
import com.br.eCormmerce.repositorys.VendasRepository;
import com.br.eCormmerce.repositorys.usuarioRepository.UsuarioRepository;

@Service
public class AvaliacaoService {
  @Autowired
  private AvaliacaoRepository avaliacaoRepository;
  @Autowired
  private UsuarioRepository usuarioRepository;
  @Autowired
  private ProdutosRepository produtosRepository;
  @Autowired 
  private VendasRepository vendasRepository;

  public List<Avaliacao>listarAvaliacoes(){
    return avaliacaoRepository.findAll();
  }

  public ResponseEntity<Object>criarAvaliacao(Avaliacao avaliacao){
    return validacaoAvaliacao(null, avaliacao);
  }

  public ResponseEntity<Object>atualizarAvaliacao(Long id, Avaliacao avaliacao){
    return validacaoAvaliacao(id, avaliacao);
  }

  public ResponseEntity<Object>deletarAvaliacao(Long id){
    if (avaliacaoRepository.existsById(id)) {
      avaliacaoRepository.deleteById(id);
      String avaliacaoExcluida = "Avaliação excluida com sucesso!";
      return ResponseEntity.ok(avaliacaoExcluida);
    }
    String avaliacaoNaoExcluida = "Avaliação nao excluida!";
    return ResponseEntity.badRequest().body(avaliacaoNaoExcluida);
  }


  public ResponseEntity<Object>validacaoAvaliacao(Long id, Avaliacao avaliacao){
    if (usuarioRepository.existsById(avaliacao.getUsuarioId())){
      if (produtosRepository.existsById(avaliacao.getProdutosId())){
        Optional<Vendas> vendaOptional = vendasRepository.findByProdutosId(avaliacao.getProdutosId());
        if (vendaOptional.isPresent()) {
          Vendas vendas = vendaOptional.get();
          if (vendas.getUsuarioId() == avaliacao.getUsuarioId()){
            if (id == null) {
              return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
            }else{
              if (avaliacaoRepository.existsById(id)) {
                avaliacao.setAvaliaca_id(id);
                return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
              }
            }
          }
        }
        String avaliacaoNaoCriada = "Esse produto não pertence a esse cliente";
        return ResponseEntity.badRequest().body(avaliacaoNaoCriada);
      }
      String avaliacaoNaoCriada = "Não existe um produto com esse id";
      return ResponseEntity.badRequest().body(avaliacaoNaoCriada);
    }
    String avaliacaoNaoCriada = "Não existe um cliente com esse id";
    return ResponseEntity.badRequest().body(avaliacaoNaoCriada);
  }
  
  
  
}
