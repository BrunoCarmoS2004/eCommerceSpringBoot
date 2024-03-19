package com.br.eCormmerce.models;

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
    private String produto_descricao;
    private String produto_imagem;
    private int produto_qtd_vendas;
    //COLOCAR AS RELAÇOES AQUI

    @OneToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor_id;
   //FAZER ISSO AI DE CIMA PARA TODOS OS QUE EU EXCLUI, INCLUSIVE O ESSE CATEGORIA
    private Categoria categoria;


    public Long getProduto_id() {
        return produto_id;
    }


    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
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


    public String getProduto_descricao() {
        return produto_descricao;
    }


    public void setProduto_descricao(String produto_descricao) {
        this.produto_descricao = produto_descricao;
    }


    public String getProduto_imagem() {
        return produto_imagem;
    }


    public void setProduto_imagem(String produto_imagem) {
        this.produto_imagem = produto_imagem;
    }


    public int getProduto_qtd_vendas() {
        return produto_qtd_vendas;
    }


    public void setProduto_qtd_vendas(int produto_qtd_vendas) {
        this.produto_qtd_vendas = produto_qtd_vendas;
    }


    public Vendedor getVendedor_id() {
        return vendedor_id;
    }


    public void setVendedor_id(Vendedor vendedor_id) {
        this.vendedor_id = vendedor_id;
    }


    public Categoria getCategoria() {
        return categoria;
    }


    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
