package com.br.eCormmerce.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Admin;
import com.br.eCormmerce.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{
    List<Categoria> findByAdminid(Long adminid);
}
