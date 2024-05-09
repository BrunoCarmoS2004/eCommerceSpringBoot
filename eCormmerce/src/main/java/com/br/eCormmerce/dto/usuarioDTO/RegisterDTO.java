package com.br.eCormmerce.dto.usuarioDTO;

import com.br.eCormmerce.models.usuario.UserRole;

public record RegisterDTO(String email, String password, String nome, String sobreNome, String numeroTelefone,String cpf, UserRole role, String cep, String logradouro, String bairro, String localidade, String uf, String pais) {

}
