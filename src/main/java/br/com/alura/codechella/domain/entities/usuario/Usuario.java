package br.com.alura.codechella.domain.entities.usuario;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Usuario {
    private static final String CPF_PATTERN = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private String cpf;
    private String nome;
    private LocalDate nascimento;
    private String email;

    public Usuario(String cpf, String nome, LocalDate nascimento, String email) {
        validateCpf(cpf);

        validateEmail(email);

        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
    }

    private static void validateEmail(String email) {
        if (email == null || !email.matches(EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    private static void validateCpf(String cpf) {
        if (cpf == null || !cpf.matches(CPF_PATTERN)) {
            throw new IllegalArgumentException("Cpf no padrão incorreto!");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
