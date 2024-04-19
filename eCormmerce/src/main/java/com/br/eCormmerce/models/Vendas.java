package com.br.eCormmerce.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Vendas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendas_id;
    
    private String vendedorId;
    
    private Long produtosId;
    
    private String clienteId;

    public Vendas(String vendedorId, Long produtosId, String clienteId) {
        this.vendedorId = vendedorId;
        this.produtosId = produtosId;
        this.clienteId = clienteId;
    }
}
