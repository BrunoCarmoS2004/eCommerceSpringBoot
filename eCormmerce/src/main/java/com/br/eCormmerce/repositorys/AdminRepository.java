package com.br.eCormmerce.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long>{
    
}
