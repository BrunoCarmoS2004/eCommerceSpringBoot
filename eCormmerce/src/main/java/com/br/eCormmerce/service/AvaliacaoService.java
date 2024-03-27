package com.br.eCormmerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Avaliacao;
import com.br.eCormmerce.repositorys.AvaliacaoRepository;
import com.br.eCormmerce.repositorys.ClienteRepository;
import com.br.eCormmerce.repositorys.ProdutosRepository;

@Service
public class AvaliacaoService {
  @Autowired
  private AvaliacaoRepository avaliacaoRepository;
  @Autowired
  private ClienteRepository clienteRepository;
  @Autowired
  private ProdutosRepository produtosRepository;

  public List<Avaliacao>listarAvaliacoes(){
    return avaliacaoRepository.findAll();
  }

  public ResponseEntity<Object>criarAvaliacao(Avaliacao avaliacao){
    if (clienteRepository.existsById(avaliacao.getCliente_id())){
      if (produtosRepository.existsById(avaliacao.getProdutos_id())){
        return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
      }
      String avaliacaoNaoCriada = "Não existe um produto com esse id";
      return ResponseEntity.badRequest().body(avaliacaoNaoCriada);
    }
    String avaliacaoNaoCriada = "Não existe um cliente com esse id";
    return ResponseEntity.badRequest().body(avaliacaoNaoCriada);
  }

  public ResponseEntity<Object>atualizarAvaliacao(Long id, Avaliacao avaliacao){
    if (avaliacaoRepository.existsById(id)) {
      avaliacao.setAvaliaca_id(id);
      return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
    }
    String idNaoEncontrado = "Id não encontrado";
    return ResponseEntity.badRequest().body(idNaoEncontrado);
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
}
