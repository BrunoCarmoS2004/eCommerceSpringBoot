package com.br.eCormmerce.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.br.eCormmerce.models.Produtos;

public interface ProdutosRepository extends JpaRepository<Produtos, Long>{
    List<Produtos> findByVendedorId(String vendedorId);
    List<Produtos> findByCategoriaId(Long categoriaId);

    @Query("SELECT p FROM Produtos p LEFT JOIN Categoria c ON p.categoriaId = c.categoria_id WHERE LOWER(p.produto_titulo) LIKE %:produto_busca% OR LOWER(c.categoria_nome) LIKE %:produto_busca%")
    List<Produtos> findAllByProduto_titulo(String produto_busca);
}
