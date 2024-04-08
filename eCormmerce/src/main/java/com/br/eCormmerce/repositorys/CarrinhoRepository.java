package com.br.eCormmerce.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.eCormmerce.models.Carrinho;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Long>{
    
}
