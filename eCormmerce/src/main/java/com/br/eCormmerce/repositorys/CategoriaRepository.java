package com.br.eCormmerce.repositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    List<Categoria> findByAdminid(String adminid);
}
