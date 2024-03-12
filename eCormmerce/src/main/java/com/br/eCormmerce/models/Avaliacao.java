package com.br.eCormmerce.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avaliaca_id;
    private String avaliaca_titulo;
    private String avaliaca_texto;
    private float avaliaca_estrelas;
    private String avaliaca_imagem;

    //COLOCAR AS RELAÇOES AQUI
    private Cliente cliente_id;
    private Produtos pruduto_id;
    
}
