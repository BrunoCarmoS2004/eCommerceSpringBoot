package com.br.eCormmerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.dto.AvaliacaoDTO;
import com.br.eCormmerce.models.Avaliacao;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.repositorys.AvaliacaoRepository;
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

  public ResponseEntity<Object>criarAvaliacao(AvaliacaoDTO avaliacaoDTO){
    return validacaoAvaliacao(null, avaliacaoDTO);
  }

  public ResponseEntity<Object>atualizarAvaliacao(Long id, AvaliacaoDTO avaliacaoDTO){
    return validacaoAvaliacao(id, avaliacaoDTO);
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


  public ResponseEntity<Object>validacaoAvaliacao(Long id, AvaliacaoDTO avaliacaoDTO){
    if (usuarioRepository.existsById(avaliacaoDTO.usuarioId())){
      if (produtosRepository.existsById(avaliacaoDTO.produtosId())){
        Optional<Vendas> vendaOptional = vendasRepository.findByProdutosId(avaliacaoDTO.produtosId());
        if (vendaOptional.isPresent()) {
          Vendas vendas = vendaOptional.get();
          if (vendas.getClienteId() == avaliacaoDTO.usuarioId()){
            if (id == null) {
              Avaliacao avaliacao = new Avaliacao(avaliacaoDTO.avaliaca_titulo(),avaliacaoDTO.avaliaca_texto(), avaliacaoDTO.avaliaca_estrelas(), avaliacaoDTO.avaliaca_imagem(), avaliacaoDTO.produtosId(), avaliacaoDTO.usuarioId());
              return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
            }else{
              if (avaliacaoRepository.existsById(id)) {
                Avaliacao avaliacao = new Avaliacao(avaliacaoDTO.avaliaca_titulo(),avaliacaoDTO.avaliaca_texto(), avaliacaoDTO.avaliaca_estrelas(), avaliacaoDTO.avaliaca_imagem(), avaliacaoDTO.produtosId(), avaliacaoDTO.usuarioId());
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
