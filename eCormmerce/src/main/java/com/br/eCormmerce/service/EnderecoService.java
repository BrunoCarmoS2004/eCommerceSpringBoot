package com.br.eCormmerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.dto.EnderecoDTO;
import com.br.eCormmerce.models.Endereco;
import com.br.eCormmerce.repositorys.EnderecoRespository;

@Service
public class EnderecoService {
  @Autowired
  private EnderecoRespository enderecoRespository;

  public List<Endereco> listarEnderecos() {
    return enderecoRespository.findAll();
  }

  public ResponseEntity<Object> criarEnderecos(EnderecoDTO enderecoDTO) {
    Endereco endereco = new Endereco(enderecoDTO.pais());
    return ResponseEntity.ok(enderecoRespository.save(endereco));
  }

  public ResponseEntity<Object> atualizarEnderecos(Long id, EnderecoDTO enderecoDTO) {
    if (!enderecoRespository.existsById(id)) {
      String enderecoNaoExiste = "Endereço nao existe!";
      return ResponseEntity.badRequest().body(enderecoNaoExiste);
    }
    Optional<Endereco> enderecoOptional = enderecoRespository.findById(id);
    Endereco endereco = enderecoOptional.get();
    endereco.setPais(enderecoDTO.pais());
    return ResponseEntity.ok(enderecoRespository.save(endereco));
    }


  public ResponseEntity<Object> deletarEnderecos(Long id) {
    if (!enderecoRespository.existsById(id)) {
      String enderecoNaoExiste = "Endereço nao existe!";
      return ResponseEntity.badRequest().body(enderecoNaoExiste);
    }
      enderecoRespository.deleteById(id);
      String enderecoExcluido = "Endereço excluido com sucesso!";
      return ResponseEntity.ok(enderecoExcluido);
    }
    
}
