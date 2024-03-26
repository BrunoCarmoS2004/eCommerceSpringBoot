package com.br.eCormmerce.service;


import java.util.List;

import org.springframework.http.ResponseEntity;

public abstract class PessoaService<Object>{
    public abstract ResponseEntity<?> criar(Object pessoa);
    public abstract List<?> listar();
    public abstract ResponseEntity<?> atualizar(Long id, Object pessoa);
    public abstract ResponseEntity<?> deletar(Long id);
}
  

