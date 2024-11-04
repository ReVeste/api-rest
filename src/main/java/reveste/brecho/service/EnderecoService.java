package reveste.brecho.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reveste.brecho.entity.Endereco;
import reveste.brecho.exception.ArgumentoInvalidoException;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.EnderecoRepository;
import reveste.brecho.service.usuario.UsuarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    private final UsuarioService usuarioService;

    public Endereco criar(Endereco endereco, Integer idUsuario) {
        if (idUsuario == null) throw new ArgumentoInvalidoException("id do usuário");

        endereco.setUsuario(usuarioService.buscarPorId(idUsuario));
        return repository.save(endereco);
    }

    public List<Endereco> listarPorUsuario(Integer idUsuario) {
        if (idUsuario == null) throw new ArgumentoInvalidoException("id do usuário");

        return repository.findAllByUsuarioIdOrderByApelido(idUsuario);
    }

    public Endereco buscarPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new NaoEncontradaException("Endereço", id));
    }

    public Endereco atualizar(int id, Endereco endereco, int idUsuario) {
        if (!repository.existsById(id)) throw new NaoEncontradaException("Endereço", id);

        endereco.setUsuario(usuarioService.buscarPorId(idUsuario));
        return repository.save(endereco);
    }

    public void deletarPorId(int id) {
        if (!repository.existsById(id)) throw new NaoEncontradaException("Endereço", id);

        repository.deleteById(id);
    }
}
