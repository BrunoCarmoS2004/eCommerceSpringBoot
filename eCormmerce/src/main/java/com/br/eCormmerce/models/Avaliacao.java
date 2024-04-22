package com.br.eCormmerce.models;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
    @NotBlank
    private String avaliaca_titulo;
    @NotBlank
    private String avaliaca_texto;
    @NotBlank
    private float avaliaca_estrelas;
    @NotBlank
    private String avaliaca_imagem;
    @NotBlank
    private Long produtosId;
    @NotBlank
    private String usuarioId;

    public Avaliacao(String avaliaca_titulo, String avaliaca_texto, float avaliaca_estrelas, String avaliaca_imagem,
            Long produtosId, String usuarioId) {
        this.avaliaca_titulo = avaliaca_titulo;
        this.avaliaca_texto = avaliaca_texto;
        this.avaliaca_estrelas = avaliaca_estrelas;
        this.avaliaca_imagem = avaliaca_imagem;
        this.produtosId = produtosId;
        this.usuarioId = usuarioId;
    }
}
