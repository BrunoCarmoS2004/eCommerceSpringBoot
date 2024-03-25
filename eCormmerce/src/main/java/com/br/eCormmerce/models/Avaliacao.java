package com.br.eCormmerce.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avaliaca_id;
    private String avaliaca_titulo;
    private String avaliaca_texto;
    private float avaliaca_estrelas;
    private String avaliaca_imagem;

    //COLOCAR AS RELAÇOES AQUI
    @OneToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produtos produtos_id;
    //FALTA O CLIENTE
    @OneToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente_id;

    public Avaliacao(String avaliaca_titulo, String avaliaca_texto, float avaliaca_estrelas, String avaliaca_imagem) {
      this.avaliaca_titulo = avaliaca_titulo;
      this.avaliaca_texto = avaliaca_texto;
      this.avaliaca_estrelas = avaliaca_estrelas;
      this.avaliaca_imagem = avaliaca_imagem;
    }
    public Avaliacao() {
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
    public Produtos getProdutos_id() {
        return produtos_id;
    }
    public void setProdutos_id(Produtos produtos_id) {
        this.produtos_id = produtos_id;
    }
    public Cliente getCliente_id() {
        return cliente_id;
    }
    public void setCliente_id(Cliente cliente_id) {
        this.cliente_id = cliente_id;
    }
    
}
