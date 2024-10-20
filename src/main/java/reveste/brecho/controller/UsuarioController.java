package reveste.brecho.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.dto.usuario.*;
import reveste.brecho.entity.usuario.Usuario;
import reveste.brecho.service.usuario.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Usuario.class)) }),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDetalheRespostaDto> buscarPorId(@PathVariable int id){
        return ResponseEntity.ok(UsuarioMapper.toDetalheDto(service.buscarPorId(id)));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioResumoDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<UsuarioResumoDto>> listar() {
        List<Usuario> usuarios = service.listar();

        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(usuarios.stream().map(UsuarioMapper::toResumoDto).toList());
    }


    // usuário se registrando no sistema
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioDetalheRespostaDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflito: e-mail ou cpf já registrado", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UsuarioDetalheRespostaDto> registrar(@RequestBody @Valid UsuarioCriacaoDto novoUsuario) {
        Usuario usuarioCriado = service.criar(UsuarioMapper.dtoToEntity(novoUsuario));
        return ResponseEntity.created(null).body(UsuarioMapper.toDetalheDto(usuarioCriado));
    }


    // usuário atualizando informações
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioDetalheRespostaDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflito: e-mail ou cpf já registrado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDetalheRespostaDto> atualizarPorId(@PathVariable int id, @RequestBody @Valid UsuarioCriacaoDto usuario) {
        Usuario usuarioAtualizado = service.atualizar(id, UsuarioMapper.dtoToEntity(usuario));
        return ResponseEntity.ok(UsuarioMapper.toDetalheDto(usuarioAtualizado));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UsuarioTokenDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "E-mail ou senha incorretos", content = @Content)
    })
    @PostMapping("/login")
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto) {
        return ResponseEntity.ok(service.autenticar(usuarioLoginDto));
    }


    // admin criando usuário
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflito: e-mail ou cpf já registrado", content = @Content)
    })
    @PostMapping("/registrar") @SecurityRequirement(name = "Bearer")
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        service.criarNovoUsuario(usuarioCriacaoDto);
        return ResponseEntity.created(null).build();
    }

}
