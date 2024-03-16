package com.br.eCormmerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Entity
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produto_id;
    private String produto_titulo;
    private float produto_preco;
    private int produto_quantidade;
    private String produto_descricao;
    private String produto_imagem;
    private int produto_qtd_vendas;

    //COLOCAR AS RELAÇOES AQUI
    private Vendedor vendedor_id;
    private Categoria cadegoria_id;

    
}
