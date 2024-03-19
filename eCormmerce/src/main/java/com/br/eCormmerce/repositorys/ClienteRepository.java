package com.br.eCormmerce.repositorys;

import com.br.eCormmerce.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    
}
