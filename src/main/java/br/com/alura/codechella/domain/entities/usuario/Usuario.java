package br.com.alura.codechella.domain.entities.usuario;

import br.com.alura.codechella.domain.Endereco;

import java.time.LocalDate;
import java.time.Period;

public class Usuario {
    private static final String CPF_PATTERN = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}";
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private static final Integer MAIOR_IDADE_MESES = 12*18;
    private static final Integer MAIOR_IDADE_ANO = 18;

    private String cpf;
    private String nome;
    private LocalDate nascimento;
    private String email;

    private Endereco endereco;

    public Usuario(String cpf, String nome, LocalDate nascimento) {
        validateCpf(cpf);
        validateDataNascimento(nascimento);

        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public Usuario(String cpf, String nome, LocalDate nascimento, String email) {
        this(cpf, nome, nascimento);

        validateEmail(email);

        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.email = email;
    }

    private Boolean isMenor(LocalDate nascimento) {
        LocalDate hoje = LocalDate.now();
        int idade = Period.between(nascimento, hoje).getYears();

        return idade < MAIOR_IDADE_ANO;
    }
    private void validateDataNascimento(LocalDate nascimento) {
        if (isMenor(nascimento)) {
            throw new IllegalArgumentException("Idade não permitida");
        }
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
