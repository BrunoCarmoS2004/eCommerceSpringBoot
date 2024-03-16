package com.br.eCormmerce.models;

import java.util.List;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;


@Entity
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produto_id;
    private String produto_titulo;
    private double produto_preco;
    private int produto_quantidade;
    private String produto_descricao;
    private String produto_imagem;
    private int produto_qtd_vendas;

    //COLOCAR AS RELAÇOES AQUI
    @OneToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    private List<Cliente> client_id;
  
  
}
