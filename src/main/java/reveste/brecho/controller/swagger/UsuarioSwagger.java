package reveste.brecho.controller.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.dto.usuario.*;

import java.util.List;

public interface UsuarioSwagger {

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<UsuarioDetalheRespostaDto> buscarPorId(@PathVariable int id);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuários encontrados com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "204", description = "Nenhum usuário encontrado", content = @Content)
    })
    @GetMapping
    ResponseEntity<List<UsuarioResumoDto>> listar();

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflito: e-mail ou cpf já registrado", content = @Content)
    })
    @PostMapping
    ResponseEntity<UsuarioDetalheRespostaDto> registrar(@RequestBody @Valid UsuarioCriacaoDto novoUsuario);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflito: e-mail ou cpf já registrado", content = @Content)
    })
    @PutMapping("/{id}")
    ResponseEntity<UsuarioDetalheRespostaDto> atualizarPorId(@PathVariable int id,
                                                             @RequestBody @Valid UsuarioCriacaoDto usuario);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content)
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deletar(@PathVariable int id);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "404", description = "E-mail ou senha incorretos", content = @Content)
    })
    @PostMapping("/login")
    ResponseEntity<UsuarioTokenDto> login(@RequestBody UsuarioLoginDto usuarioLoginDto);

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content),
            @ApiResponse(responseCode = "409", description = "Conflito: e-mail ou cpf já registrado", content = @Content)
    })
    @PostMapping("/registrar") @SecurityRequirement(name = "Bearer")
    ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto);
}
