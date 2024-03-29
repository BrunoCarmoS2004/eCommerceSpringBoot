package com.br.eCormmerce.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Produtos;
import com.br.eCormmerce.models.Vendas;

public interface VendasRepository extends JpaRepository<Vendas, Long>{
  //CRIAR UM EXISTSBYCLIENTEID
  List<Vendas> findByVendedorId(Long id);
  List<Cliente> findByProdutosId(Long id);
  List<Produtos> findByClienteId(Long id);
}
