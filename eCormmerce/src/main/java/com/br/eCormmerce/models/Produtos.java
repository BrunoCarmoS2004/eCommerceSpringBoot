package com.br.eCormmerce.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    @OneToOne
    @JoinColumn(name = "vendedor_id", nullable = false)
    private Vendedor vendedor_id;

    @OneToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria_id;

    @OneToMany(mappedBy = "produtos_id")
    private List<Avaliacao>avaliacao;


    public Produtos(String produto_titulo, double produto_preco, int produto_quantidade, String produto_descricao,
            String produto_imagem, int produto_qtd_vendas, Vendedor vendedor_id, Categoria categoria_id) {
        this.produto_titulo = produto_titulo;
        this.produto_preco = produto_preco;
        this.produto_quantidade = produto_quantidade;
        this.produto_descricao = produto_descricao;
        this.produto_imagem = produto_imagem;
        this.produto_qtd_vendas = produto_qtd_vendas;
        this.vendedor_id = vendedor_id;
        this.categoria_id = categoria_id;
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


    public Categoria getCategoria_id() {
        return categoria_id;
    }


    public void setCategoria_id(Categoria categoria_id) {
        this.categoria_id = categoria_id;
    }


    public List<Avaliacao> getAvaliacao() {
        return avaliacao;
    }


    public void setAvaliacao(List<Avaliacao> avaliacao) {
        this.avaliacao = avaliacao;
    }

    public void setProduto_id(Long produto_id) {
        this.produto_id = produto_id;
    }

}
