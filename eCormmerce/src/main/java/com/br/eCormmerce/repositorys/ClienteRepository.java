package com.br.eCormmerce.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.models.Vendas;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
  Boolean existsByCpf(String cpf);
  Optional<Cliente> findByCpf(String cpf);
}
