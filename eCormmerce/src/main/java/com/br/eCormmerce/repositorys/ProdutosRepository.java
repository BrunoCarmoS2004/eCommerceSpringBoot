package com.br.eCormmerce.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.eCormmerce.models.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
    List<Produtos> findByVendedorId(Long vendedorId);
    List<Produtos> findByCategoriaId(Long categoriaId);

    @Query("SELECT u FROM Produtos u WHERE LOWER(u.produto_titulo) LIKE %:produto_busca%")
    List<Produtos> findAllByProduto_titulo(String produto_busca);
}
