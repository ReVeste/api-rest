package reveste.brecho.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reveste.brecho.entity.Feedback;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.FeedbackRepository;
import reveste.brecho.service.usuario.UsuarioService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository repository;
    private final UsuarioService usuarioService;

    public List<Feedback> listar() {
        return repository.findAll();
    }

    public Feedback buscarPorId(int id) {
        Optional<Feedback> feedbackOpt = repository.findById(id);

        if (feedbackOpt.isEmpty()) throw new NaoEncontradaException("Feedback", id);

        return feedbackOpt.get();
    }

    public Feedback criar(Feedback feedback, int idUsuario) {
        feedback.setUsuario(usuarioService.buscarPorId(idUsuario));
        return repository.save(feedback);
    }

    public Feedback atualizar(Feedback feedback, int idUsuario) {
        if (!repository.existsById(feedback.getId())) {
            throw new NaoEncontradaException("Feedback");
        }

        feedback.setUsuario(usuarioService.buscarPorId(idUsuario));
        return repository.save(feedback);
    }

    public void deletar(int id) {
        if (!repository.existsById(id)) {
            throw new NaoEncontradaException("Feedback");
        }

        repository.deleteById(id);
    }
}
