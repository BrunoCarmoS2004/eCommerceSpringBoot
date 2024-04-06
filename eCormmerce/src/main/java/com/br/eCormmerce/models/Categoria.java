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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
