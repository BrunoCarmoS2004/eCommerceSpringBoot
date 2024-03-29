package com.br.eCormmerce.models;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


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

    public Avaliacao() {
    }

    public Avaliacao(String avaliaca_titulo, String avaliaca_texto, float avaliaca_estrelas, String avaliaca_imagem,
            Long produtosId, Long clienteId) {
        this.avaliaca_titulo = avaliaca_titulo;
        this.avaliaca_texto = avaliaca_texto;
        this.avaliaca_estrelas = avaliaca_estrelas;
        this.avaliaca_imagem = avaliaca_imagem;
        this.produtosId = produtosId;
        this.clienteId = clienteId;
    }

    public Long getAvaliaca_id() {
        return avaliaca_id;
    }

    public void setAvaliaca_id(Long avaliaca_id) {
        this.avaliaca_id = avaliaca_id;
    }

    public String getAvaliaca_titulo() {
        return avaliaca_titulo;
    }

    public void setAvaliaca_titulo(String avaliaca_titulo) {
        this.avaliaca_titulo = avaliaca_titulo;
    }

    public String getAvaliaca_texto() {
        return avaliaca_texto;
    }

    public void setAvaliaca_texto(String avaliaca_texto) {
        this.avaliaca_texto = avaliaca_texto;
    }

    public float getAvaliaca_estrelas() {
        return avaliaca_estrelas;
    }

    public void setAvaliaca_estrelas(float avaliaca_estrelas) {
        this.avaliaca_estrelas = avaliaca_estrelas;
    }

    public String getAvaliaca_imagem() {
        return avaliaca_imagem;
    }

    public void setAvaliaca_imagem(String avaliaca_imagem) {
        this.avaliaca_imagem = avaliaca_imagem;
    }

    public Long getProdutos_id() {
        return produtosId;
    }

    public void setProdutos_id(Long produtosId) {
        this.produtosId = produtosId;
    }

    public Long getCliente_id() {
        return clienteId;
    }

    public void setCliente_id(Long clienteId) {
        this.clienteId = clienteId;
    }
}
