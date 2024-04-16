package com.br.eCormmerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Admin extends Pessoa{
    //CRIAR CLASSE PARA HERANÇA DOS TIPOS SEMELHANTES 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long admin_id;
    @OneToMany(mappedBy = "adminid", fetch = FetchType.LAZY)
    private List<Categoria> categorias;
    
    private Long enderecoId;
    
    @ManyToMany
    private List<Endereco> enderecos = new ArrayList<>();
    public Admin(String nome, String cpf) {
        super(nome, cpf);
    }

    public Admin(String nome, String cpf, double saldo, String cep, String rua, Long enderecoId) {
        super(nome, cpf, saldo,rua,cep);
        this.enderecoId = enderecoId;
    }
}
