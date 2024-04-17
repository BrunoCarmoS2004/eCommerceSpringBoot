package com.br.eCormmerce.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cliente extends Pessoa{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "clienteId", fetch = FetchType.LAZY)
    private List<Avaliacao> avaliacoes;

    @OneToOne(mappedBy = "usuario", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Carrinho carrinho;

    @OneToMany(mappedBy = "usuarioId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Vendas> vendas;

    private Long enderecoId;
    
    @ManyToMany
    private List<Endereco> enderecos = new ArrayList<>();
    

    public Cliente(String nome, String cpf, double saldo, String cep, String rua, Long enderecoId) {
        super(nome, cpf, saldo,rua,cep);
        this.enderecoId = enderecoId;
    }
    public Cliente(String nome, String cpf) {
        super(nome, cpf);
    }
}
