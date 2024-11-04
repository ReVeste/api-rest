package reveste.brecho.service.usuario;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reveste.brecho.api.configuration.security.jwt.GerenciadorTokenJwt;
import reveste.brecho.dto.usuario.UsuarioCriacaoDto;
import reveste.brecho.dto.usuario.UsuarioLoginDto;
import reveste.brecho.dto.usuario.UsuarioTokenDto;
import reveste.brecho.entity.Usuario;
import reveste.brecho.dto.usuario.UsuarioMapper;
import reveste.brecho.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;

    public Usuario buscarPorId(Integer id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if (usuarioOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        return usuarioOpt.get();
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario criar(Usuario usuario) {
        if (usuarioRepository.existsByEmailOrCpf(usuario.getEmail(), usuario.getCpf())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Conflito: e-mail ou cpf já registrado");
        }

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizar(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi localizado um usuário com esse id no banco");
        }

        if (usuarioRepository.existsByEmailOrCpfAndIdNot(usuario.getEmail(), usuario.getCpf(), usuario.getId())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já há um usuário com esse e-mail ou CPF");
        }

        return usuarioRepository.save(usuario);
    }

    public void deletarPorId(int id) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        usuarioExistente.setAtivo(false);
        usuarioRepository.save(usuarioExistente);
    }

    public void criarNovoUsuario(UsuarioCriacaoDto usuarioCriacaoDto) {
        final Usuario novoUsuario = UsuarioMapper.of(usuarioCriacaoDto);

        novoUsuario.setSenha(passwordEncoder.encode(novoUsuario.getSenha()));

        usuarioRepository.save(novoUsuario);
    }

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLoginDto) {

        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLoginDto.getEmail(), usuarioLoginDto.getSenha());

        final Authentication authentication = this.authenticationManager.authenticate(credentials);

        Usuario usuarioAutenticado =
                usuarioRepository.findByEmailAndAtivo(usuarioLoginDto.getEmail(), true)
                        .orElseThrow(
                                () -> new ResponseStatusException(404, "Email do usuário não cadastrado ou usuário inativo", null)
                        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.generateToken(authentication);

        return UsuarioMapper.of(usuarioAutenticado, token);
    }

}
