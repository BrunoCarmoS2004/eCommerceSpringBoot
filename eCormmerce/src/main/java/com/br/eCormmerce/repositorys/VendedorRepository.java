package com.br.eCormmerce.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{
    
}
