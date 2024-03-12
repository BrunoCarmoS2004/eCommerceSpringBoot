package com.br.eCormmerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Admin {
    //CRIAR CLASSE PARA HERANÇA DOS TIPOS SEMELHANTES 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;
    private String admin_nome;
    private Float admin_saldo;

    public Admin() {
    }
    public Admin(Long admin_id, String admin_nome, Float admin_saldo) {
        this.admin_id = admin_id;
        this.admin_nome = admin_nome;
        this.admin_saldo = admin_saldo;
    }
    public Long getadmin_Id() {
        return admin_id;
    }
    public void setadmin_Id(Long admin_id) {
        this.admin_id = admin_id;
    }
    public String getAdmin_nome() {
        return admin_nome;
    }
    public void setAdmin_nome(String admin_nome) {
        this.admin_nome = admin_nome;
    }
    public Float getAdmin_saldo() {
        return admin_saldo;
    }
    public void setAdmin_saldo(Float admin_saldo) {
        this.admin_saldo = admin_saldo;
    }
    
    
}
