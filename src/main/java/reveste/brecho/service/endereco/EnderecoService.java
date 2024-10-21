package reveste.brecho.service.endereco;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reveste.brecho.entity.endereco.Endereco;
import reveste.brecho.repository.EnderecoRepository;
import reveste.brecho.service.usuario.UsuarioService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    private final UsuarioService usuarioService;

    public Endereco criar(Endereco endereco, Integer idUsuario) {
        endereco.setUsuario(usuarioService.buscarPorId(idUsuario));
        return repository.save(endereco);
    }

    public List<Endereco> listarPorUsuario(Integer idUsuario) {
        return repository.findAllByUsuarioIdOrderByApelido(idUsuario);
    }

    public Endereco buscarPorId(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado"));
    }

    public Endereco atualizar(int id, Endereco endereco, int idUsuario) {
        if (!repository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");

        endereco.setUsuario(usuarioService.buscarPorId(idUsuario));
        endereco.setId(id);

        return repository.save(endereco);
    }

    public void deletarPorId(int id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Endereço não encontrado");
        }

        repository.deleteById(id);
    }
}
