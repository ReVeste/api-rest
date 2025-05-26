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
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.pedido.PedidoMapper;
import reveste.brecho.entity.Feedback;
import reveste.brecho.entity.ItemPedido;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Usuario;
import reveste.brecho.service.FeedbackService;
import reveste.brecho.service.ItemPedidoService;
import reveste.brecho.service.PedidoService;
import reveste.brecho.service.usuario.UsuarioService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feedbacks")
public class FeedbackController implements FeedbackSwagger{

    private final FeedbackService service;
    private final PedidoService pedidoService;
    private final ItemPedidoService itemPedidoService;
    private final UsuarioService usuarioService;

    @Override
    public ResponseEntity<List<FeedbackRespostaDto>> listar() {
        List<Feedback> feedbacks = service.listar();

        return feedbacks.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(feedbacks.stream().map(FeedbackMapper::entityToDto).toList());
    }

    @Override
    public ResponseEntity<FeedbackRespostaDto> buscarPorId(@PathVariable int id) {
        return ResponseEntity.ok(FeedbackMapper.entityToDto(service.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<FeedbackRespostaDto> criar(@RequestBody @Valid FeedbackRequisicaoDto feedbackDto) {

        PedidoDto pedidoDto = pedidoService.buscarPedido(feedbackDto.getPedido());
        Pedido pedido = PedidoMapper.pedidoDtoToEntidade(pedidoDto);
        ItemPedido itemPedido = itemPedidoService.buscarItemPedido(feedbackDto.getItemPedido());
        Usuario usuario = usuarioService.buscarPorId(feedbackDto.getUsuario());

        Feedback feedback = FeedbackMapper.criacaoDtoToEntity(feedbackDto, itemPedido, pedido, usuario);
        Feedback feedbackCriado = service.criar(feedback);

        return ResponseEntity.created(null).body(FeedbackMapper.entityToDto(feedbackCriado));
    }

//    @Override
//    public ResponseEntity<FeedbackRespostaDto> atualizar(@RequestBody @Valid FeedbackRequisicaoDto feedbackDto) {
//        Feedback feedback = FeedbackMapper.atualizacaoDtoToEntity(feedbackDto);
//        Feedback feedbackAtualizado = service.atualizar(feedback, feedbackDto.getIdUsuario());
//        return ResponseEntity.created(null).body(FeedbackMapper.toDto(feedbackAtualizado));
//    }

//    @Override
//    public ResponseEntity<Void> deletar(@PathVariable int id) {
//        service.deletar(id);
//        return ResponseEntity.noContent().build();
//    }

}
