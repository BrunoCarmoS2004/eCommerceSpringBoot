package com.br.eCormmerce.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    
}
