package br.com.alura.codechella.infra.controller.dtos;

import java.time.LocalDate;

public record UsuarioResponseDto(
        String cpf,
        String nome,
        LocalDate nascimento,
        String email) {
}
