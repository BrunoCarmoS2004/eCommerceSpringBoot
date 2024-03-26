package com.br.eCormmerce.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

public interface PessoaService<Object>{
    public abstract List<?> listarUsuario();
    public abstract ResponseEntity<?> criarUsuario(Object pessoa);
    public abstract ResponseEntity<?> atualizarUsuario(Long id, Object pessoa);
    public abstract ResponseEntity<?> deletarUsuario(Long id);
}
  

