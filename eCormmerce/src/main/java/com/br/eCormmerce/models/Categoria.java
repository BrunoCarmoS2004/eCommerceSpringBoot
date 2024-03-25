package com.br.eCormmerce.models;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoria_id;
    private String categoria_nome;

    @OneToMany(mappedBy = "categoria_id")
    private List<Produtos> produtos;
    @OneToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin_id;

    public Categoria(Long categoria_id, String categoria_nome, Admin admin_id) {
        this.categoria_id = categoria_id;
        this.categoria_nome = categoria_nome;
        this.admin_id = admin_id;
    }
    public Categoria() {
    }
    public Long getCategoria_id() {
        return categoria_id;
    }
    public String getCategoria_nome() {
        return categoria_nome;
    }
    public void setCategoria_nome(String categoria_nome) {
        this.categoria_nome = categoria_nome;
    }
    public List<Produtos> getProdutos() {
        return produtos;
    }
    public void setProdutos(List<Produtos> produtos) {
        this.produtos = produtos;
    }
    public Admin getAdmin_id() {
        return admin_id;
    }
    public void setAdmin_id(Admin admin_id) {
        this.admin_id = admin_id;
    }
    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }
}
