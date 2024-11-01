package reveste.brecho.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reveste.brecho.controller.swagger.FeedbackSwagger;
import reveste.brecho.dto.feedback.FeedbackRequisicaoDto;
import reveste.brecho.dto.feedback.FeedbackRespostaDto;
import reveste.brecho.dto.feedback.FeedbackMapper;
import reveste.brecho.entity.Feedback;
import reveste.brecho.service.FeedbackService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
public class FeedbackController implements FeedbackSwagger{

    private final FeedbackService service;

    public ResponseEntity<List<FeedbackRespostaDto>> listar() {
        List<Feedback> feedbacks = service.listar();

        return feedbacks.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(feedbacks.stream().map(FeedbackMapper::toDto).toList());
    }

    public ResponseEntity<FeedbackRespostaDto> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(FeedbackMapper.toDto(service.buscarPorId(id)));
    }

    public ResponseEntity<FeedbackRespostaDto> criar(@RequestBody @Valid FeedbackRequisicaoDto feedbackDto) {
        Feedback feedback = FeedbackMapper.criacaoDtoToEntity(feedbackDto);
        Feedback feedbackCriado = service.criar(feedback, feedbackDto.getIdUsuario());
        return ResponseEntity.created(null).body(FeedbackMapper.toDto(feedbackCriado));
    }

    public ResponseEntity<FeedbackRespostaDto> atualizar(@RequestBody @Valid FeedbackRequisicaoDto feedbackDto) {
        Feedback feedback = FeedbackMapper.atualizacaoDtoToEntity(feedbackDto);
        Feedback feedbackAtualizado = service.atualizar(feedback, feedbackDto.getIdUsuario());
        return ResponseEntity.created(null).body(FeedbackMapper.toDto(feedbackAtualizado));
    }

    public ResponseEntity<Void> deletar(@PathVariable int id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
