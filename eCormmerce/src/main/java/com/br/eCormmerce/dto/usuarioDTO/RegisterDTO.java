package com.br.eCormmerce.dto.usuarioDTO;

import com.br.eCormmerce.models.usuario.UserRole;

public record RegisterDTO(String email, String password ,String nome, String cpf, UserRole role, double saldo, String cep, String rua, Long enderecoId) {

}
