package com.br.eCormmerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.eCormmerce.models.Endereco;
import com.br.eCormmerce.repositorys.EnderecoRespository;

@Service
public class EnderecoService {
  @Autowired
  private EnderecoRespository enderecoRespository;
  public List<Endereco>listarEnderecos(){
    return enderecoRespository.findAll();
  }

  public ResponseEntity<Object>criarEnderecos(Endereco endereco){
    return ResponseEntity.ok(enderecoRespository.save(endereco));
  }

  public ResponseEntity<Object>atualizarEnderecos(Long id, Endereco endereco){
    endereco.setId(id);
    return ResponseEntity.ok(enderecoRespository.save(endereco));
  }

  public ResponseEntity<Object>deletarEnderecos(Long id){
    if (enderecoRespository.existsById(id)) {
      enderecoRespository.deleteById(id);
      String enderecoExcluido = "Endereço excluido com sucesso!";
      return ResponseEntity.ok(enderecoExcluido);
    }
    String enderecoExcluida = "Endereço nao excluida!";
    return ResponseEntity.badRequest().body(enderecoExcluida);
  }
}
