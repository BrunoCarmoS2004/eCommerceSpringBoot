package com.br.eCormmerce.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long>{
  //CRIAR UM EXISTSBYCLIENTEID
  Optional<Vendas> findByVendedorId(Long id);
  Optional<Vendas> findByClienteId(Long id);
  Optional<Vendas> findByProdutosId(Long id);
}
