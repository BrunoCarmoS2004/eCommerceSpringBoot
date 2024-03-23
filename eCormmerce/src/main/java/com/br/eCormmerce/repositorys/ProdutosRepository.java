package com.br.eCormmerce.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
    List<Produtos> findByVendedorId(Long vendedorId);
    List<Produtos> findByCategoriaId(Long categoriaId);
}
