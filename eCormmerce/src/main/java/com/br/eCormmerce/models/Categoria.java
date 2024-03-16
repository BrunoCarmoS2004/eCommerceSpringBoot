package com.br.eCormmerce.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoria_id;
    private String categoria_nome;
    
    //COLOCAR AS RELAÇOES AQUI
    @OneToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    
    @OneToOne(mappedBy = "categoria")
    private Produtos produto;
   
    
   
}
