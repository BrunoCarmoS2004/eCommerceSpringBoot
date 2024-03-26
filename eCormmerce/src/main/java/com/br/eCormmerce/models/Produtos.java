package com.br.eCormmerce.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class Produtos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long produto_id;
    private String produto_titulo;
    private double produto_preco;
    private int produto_quantidade;

    @OneToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor_id;

    public Produtos(String produto_titulo, double produto_preco, int produto_quantidade, Vendedor vendedor_id) {
        this.produto_titulo = produto_titulo;
        this.produto_preco = produto_preco;
        this.produto_quantidade = produto_quantidade;
        this.vendedor_id = vendedor_id;
    }

    public Produtos() {
    }

    public Long getProduto_id() {
        return produto_id;
    }

    public String getProduto_titulo() {
        return produto_titulo;
    }

    public void setProduto_titulo(String produto_titulo) {
        this.produto_titulo = produto_titulo;
    }

    public double getProduto_preco() {
        return produto_preco;
    }

    public void setProduto_preco(double produto_preco) {
        this.produto_preco = produto_preco;
    }

    public int getProduto_quantidade() {
        return produto_quantidade;
    }

    public void setProduto_quantidade(int produto_quantidade) {
        this.produto_quantidade = produto_quantidade;
    }

    public Vendedor getVendedor_id() {
        return vendedor_id;
    }

    public void setVendedor_id(Vendedor vendedor_id) {
        this.vendedor_id = vendedor_id;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

}
