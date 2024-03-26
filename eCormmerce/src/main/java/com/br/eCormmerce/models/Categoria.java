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

    @OneToMany(mappedBy = "categoriaId", fetch = FetchType.LAZY)
    private List<Produtos> produtos;
    
    private Long adminid;
    


    public Categoria(String categoria_nome, Long adminid) {
        this.categoria_nome = categoria_nome;
        this.adminid = adminid;
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
    public Long getAdminid() {
        return adminid;
    }
    public void setAdminid(Long adminid) {
        this.adminid = adminid;
    }
    public void setCategoria_id(Long categoria_id) {
        this.categoria_id = categoria_id;
    }

    /*
    public class Response {
    private List<Categoria> categorias;
    private String message;

    // getters e setters
}

public Response listarCategoriaPorAdmin(Long id){
    Response response = new Response();
    if(adminRepository.existsById(id)){
      response.setCategorias(categoriaRepository.findByAdminId(id));
    } else {
      response.setMessage("Admin com id " + id + " não existe.");
    }
    return response;
} */
}
