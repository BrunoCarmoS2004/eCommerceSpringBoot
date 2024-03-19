package com.br.eCormmerce.models;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoria_id;
    private String categoria_nome;

    @OneToMany(mappedBy = "categoria_id")
    private List<Produtos> produtos;
    @OneToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin_id;
    
    //COLOCAR AS RELAÇOES AQUI
}
