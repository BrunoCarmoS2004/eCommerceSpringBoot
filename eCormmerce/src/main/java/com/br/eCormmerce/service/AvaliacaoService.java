package com.br.eCormmerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.dto.AvaliacaoDTO;
import com.br.eCormmerce.models.Avaliacao;
import com.br.eCormmerce.models.Vendas;
import com.br.eCormmerce.models.usuario.Usuario;
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

  public ResponseEntity<Object>criarAvaliacao(Long id, AvaliacaoDTO avaliacaoDTO){
    return validacaoAvaliacao(id, avaliacaoDTO, "criar");
  }

  public ResponseEntity<Object>atualizarAvaliacao(Long id, AvaliacaoDTO avaliacaoDTO){
    return validacaoAvaliacao(id, avaliacaoDTO, "atualizar");
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

  public ResponseEntity<Object>validacaoAvaliacao(Long id, AvaliacaoDTO avaliacaoDTO, String tipo){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) authentication.getPrincipal();
    UserDetails usuarioDetails = usuarioRepository.findByEmail(userDetails.getUsername());
    Usuario usuario = (Usuario) usuarioDetails;
    if(!produtosRepository.existsById(id)){
      String avaliacaoNaoCriada = "Não existe um produto com esse id";
      return ResponseEntity.badRequest().body(avaliacaoNaoCriada);
    }
    Optional<Vendas> vendaOptional = vendasRepository.findByProdutosId(id);
    if (vendaOptional.isPresent()) {
      Vendas vendas = vendaOptional.get();
      if (vendas.getClienteId() == usuario.getId()){
        //A variavel tipo vem dos métodos criarAvaliacao e atualizarAvaliacao
        if (tipo == "criar") {
          /*ID = PRODUTO_ID*/
          Avaliacao avaliacao = new Avaliacao(avaliacaoDTO.avaliaca_titulo(),avaliacaoDTO.avaliaca_texto(), avaliacaoDTO.avaliaca_estrelas(), avaliacaoDTO.avaliaca_imagem(), id, usuario.getId());
          return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
        }else{
            if (avaliacaoRepository.existsById(id)) {
            /*ID = AVALIAÇÃO_ID*/
            Optional<Avaliacao> avaliacaoOptional = avaliacaoRepository.findById(id);
            Avaliacao avaliacao = avaliacaoOptional.get();
            avaliacao.setAvaliaca_estrelas(avaliacaoDTO.avaliaca_estrelas());
            avaliacao.setAvaliaca_imagem(avaliacaoDTO.avaliaca_imagem());
            avaliacao.setAvaliaca_texto(avaliacaoDTO.avaliaca_texto());
            avaliacao.setAvaliaca_titulo(avaliacaoDTO.avaliaca_titulo());
            avaliacao.setProdutosId(id);
            avaliacao.setUsuarioId(usuario.getId());
            return ResponseEntity.ok(avaliacaoRepository.save(avaliacao));
          }
        }
      }
    }
    String avaliacaoNaoCriada = "Esse produto não pertence a esse cliente";
    return ResponseEntity.badRequest().body(avaliacaoNaoCriada);
  }
}
