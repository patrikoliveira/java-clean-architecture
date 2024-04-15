package br.com.alura.codechella.infra.controller;

import br.com.alura.codechella.application.usecases.CriarUsuario;
import br.com.alura.codechella.application.usecases.ListarUsuarios;
import br.com.alura.codechella.domain.entities.usuario.Usuario;
import br.com.alura.codechella.infra.controller.dtos.UsuarioRequestDto;
import br.com.alura.codechella.infra.controller.dtos.UsuarioResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final CriarUsuario criarUsuario;
    private final ListarUsuarios listarUsuarios;

    public UsuarioController(CriarUsuario criarUsuario, ListarUsuarios listarUsuarios) {
        this.criarUsuario = criarUsuario;
        this.listarUsuarios = listarUsuarios;
    }

    @PostMapping
    public UsuarioResponseDto cadastrarUsuario(@RequestBody UsuarioRequestDto dto) {
        Usuario salvo = criarUsuario.cadastrarUsuario(new Usuario(dto.cpf(), dto.nome(), dto.nascimento(), dto.email()));

        return new UsuarioResponseDto(salvo.getCpf(), salvo.getNome(), salvo.getNascimento(), salvo.getEmail());
    }

    @GetMapping
    public List<UsuarioResponseDto> listarUsuarios() {
        return listarUsuarios.obterTodosUsuarios()
                .stream()
                .map(u -> new UsuarioResponseDto(u.getCpf(), u.getNome(), u.getNascimento(), u.getEmail()))
                .collect(Collectors.toList());
    }
}
