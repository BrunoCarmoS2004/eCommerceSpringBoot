package com.br.eCormmerce.repositorys;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Admin;


public interface AdminRepository extends JpaRepository<Admin, Long>{
    Boolean existsByCpf(String cpf);
    Optional<Admin> findByCpf(String cpf);
}
