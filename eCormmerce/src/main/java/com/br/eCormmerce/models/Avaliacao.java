package com.br.eCormmerce.models;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avaliaca_id;
    private String avaliaca_titulo;
    private String avaliaca_texto;
    private float avaliaca_estrelas;
    private String avaliaca_imagem;

    private Long produtosId;
   
    private Long clienteId;

    public Avaliacao(String avaliaca_titulo, String avaliaca_texto, float avaliaca_estrelas, String avaliaca_imagem,
            Long produtosId, Long clienteId) {
        this.avaliaca_titulo = avaliaca_titulo;
        this.avaliaca_texto = avaliaca_texto;
        this.avaliaca_estrelas = avaliaca_estrelas;
        this.avaliaca_imagem = avaliaca_imagem;
        this.produtosId = produtosId;
        this.clienteId = clienteId;
    }
}
