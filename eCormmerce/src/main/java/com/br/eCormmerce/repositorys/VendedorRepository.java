package com.br.eCormmerce.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Vendedor;

public interface VendedorRepository extends JpaRepository<Vendedor, Long>{
    Boolean existsByCpf(String cpf);
    Optional<Vendedor> findByCpf(String cpf);
}
