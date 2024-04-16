package com.br.eCormmerce.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Endereco;

public interface EnderecoRespository extends JpaRepository<Endereco, Long> {
  
}
