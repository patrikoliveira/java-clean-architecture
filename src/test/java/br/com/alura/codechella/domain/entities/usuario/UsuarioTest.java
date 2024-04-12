package br.com.alura.codechella.domain.entities.usuario;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UsuarioTest {
    @Test
    public void naoDeveCadastrarUsuarioComCpfNoFormatoInvalido() {
        Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> new Usuario("123456789-99", "Patrik", LocalDate.parse("1983-10-03"), "email@email.com"));

        Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> new Usuario("12345678999", "Patrik", LocalDate.parse("1983-10-03"), "email@email.com"));

        Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> new Usuario("123.456789-99", "Patrik", LocalDate.parse("1983-10-03"), "email@email.com"));

        Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> new Usuario(null, "Patrik", LocalDate.parse("1983-10-03"), "email@email.com"));

        Assertions.assertThrows(
                    IllegalArgumentException.class,
                    () -> new Usuario("", "Patrik", LocalDate.parse("1983-10-03"), "email@email.com"));
    }

    @Test
    public void naoDeveCadastrarUsuarioComEmailInvalido() {
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario("123.456.789-00", "Patrik", LocalDate.parse("1983-10-03"), "patrik"));
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario("123.456.789-00", "Patrik", LocalDate.parse("1983-10-03"), null));
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario("123.456.789-00", "Patrik", LocalDate.parse("1983-10-03"), "patrik@com"));
    }

    @Test
    public void naoDeveCadastrarUsuarioMenorDeDezoitoAnos() {
        LocalDate dataNascimento = LocalDate.now().minusYears(17);
        IllegalArgumentException exception = Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> new Usuario("123.456.789-00", "Patrik", dataNascimento, "patrik@com.com"));

        Assertions.assertEquals("Idade não permitida", exception.getMessage());
    }

    @Test
    public void deveCriarUsuarioUsandoFabricaDeUsuario() {
        FabricaDeUsuario fabrica = new FabricaDeUsuario();
        Usuario usuario = fabrica.comNomeCpfNascimento("Patrik", "123.456.789-00",LocalDate.parse("1983-10-03"));

        Assertions.assertEquals("Patrik", usuario.getNome());
        Assertions.assertEquals("123.456.789-00", usuario.getCpf());

        usuario = fabrica.incluiEndereco("12345-000", 40, "apto 201");
        Assertions.assertEquals("apto 201", usuario.getEndereco().getComplemento());
    }
}
