package com.br.eCormmerce.models;

import java.util.ArrayList;
import java.util.List;

import com.br.eCormmerce.models.usuario.Usuario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrinho_id;
    @NotBlank
    private Double total = 0.0;
    @NotBlank
    @OneToOne
    private Usuario usuario;
    
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Produtos> produto = new ArrayList<>();

    public Carrinho(Usuario usuario, List<Produtos> produto) {
        this.usuario = usuario;
        this.produto = produto;
    }
    public Carrinho(List<Produtos> produto) {
        this.produto = produto;
    }
    public Carrinho(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getUsuario() {
        return usuario.getNome();
    }
    public String getUsuarioId() {
        return usuario.getId();
    }

}
