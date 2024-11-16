package reveste.brecho.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.controller.swagger.UsuarioSwagger;
import reveste.brecho.dto.usuario.*;
import reveste.brecho.entity.Usuario;
import reveste.brecho.service.usuario.UsuarioService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UsuarioController implements UsuarioSwagger {

    private final UsuarioService service;

    @Override
    public ResponseEntity<UsuarioDetalheRespostaDto> buscarPorId(@PathVariable int id){
        return ResponseEntity.ok(UsuarioMapper.toDetalheDto(service.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<List<UsuarioResumoDto>> listar() {
        List<Usuario> usuarios = service.listar();

        return usuarios.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(usuarios.stream().map(UsuarioMapper::toResumoDto).toList());
    }

    @Override
    public ResponseEntity<UsuarioDetalheRespostaDto> registrar(@RequestBody @Valid UsuarioCriacaoDto novoUsuario) {
        Usuario usuarioCriado = service.criar(UsuarioMapper.dtoToEntity(novoUsuario));
        return ResponseEntity.created(null).body(UsuarioMapper.toDetalheDto(usuarioCriado));
    }

    @Override
    public ResponseEntity<UsuarioDetalheRespostaDto> atualizarPorId(@PathVariable int id,
                                                                    @RequestBody @Valid UsuarioCriacaoDto usuario) {

        Usuario usuarioAtualizado = service.atualizar(UsuarioMapper.atualizacaoToEntity(usuario, id));
        return ResponseEntity.ok(UsuarioMapper.toDetalheDto(usuarioAtualizado));
    }

    @Override
    public ResponseEntity<Void> deletar(@PathVariable int id){
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<UsuarioTokenDto> login(@RequestBody @Valid UsuarioLoginDto usuarioLoginDto) {
        return ResponseEntity.ok(service.autenticar(usuarioLoginDto));
    }

    @Override
    public ResponseEntity<Void> criar(@RequestBody @Valid UsuarioCriacaoDto usuarioCriacaoDto) {
        service.criarNovoUsuario(usuarioCriacaoDto);
        return ResponseEntity.created(null).build();
    }

}