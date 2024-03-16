package com.br.eCormmerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.util.List;

import com.br.eCormmerce.models.Pessoa;
@Entity
public class Admin extends Pessoa{
    //CRIAR CLASSE PARA HERANÇA DOS TIPOS SEMELHANTES 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;
    @OneToOne(mappedBy = "admin")
    private Categoria categoria;

    public Admin(String nome, String cpf) {
        super(nome, cpf);
    }

    public Long getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Long admin_id) {
        this.admin_id = admin_id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    
    
}
