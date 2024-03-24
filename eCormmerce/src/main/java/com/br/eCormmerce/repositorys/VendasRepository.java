package com.br.eCormmerce.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long>{
  
}
