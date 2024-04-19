package com.br.eCormmerce.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long>{
  //CRIAR UM EXISTSBYCLIENTEID
  Optional<Vendas> findByVendedorId(String id);
  Optional<Vendas> findByClienteId(String id);
  Optional<Vendas> findByProdutosId(Long id);
}
