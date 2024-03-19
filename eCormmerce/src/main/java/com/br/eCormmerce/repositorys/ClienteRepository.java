package com.br.eCormmerce.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    
}
