package reveste.brecho.dto.feedback;

import reveste.brecho.entity.*;
import reveste.brecho.service.PedidoService;

import java.util.List;
import java.util.stream.Collectors;

public class FeedbackMapper {

    public static Feedback criacaoDtoToEntity(
            FeedbackRequisicaoDto dto,
            ItemPedido itemPedido,
            Pedido pedido,
            Usuario usuario
    ) {
        if (dto == null) return null;

        Feedback feedback = Feedback.builder()
                .comentario(dto.getComentario())
                .itemPedido(itemPedido)
                .pedido(pedido)
                .usuario(usuario)
                .build();

        List<ImagensFeedback> imagensFeedbacks = dto.getImagensFeedback().stream()
                .map(url -> new ImagensFeedback(feedback, pedido, usuario, itemPedido, url))
                .collect(Collectors.toList());

        feedback.setImagensFeedbacks(imagensFeedbacks);

        return feedback;
    }


    public static FeedbackRespostaDto entityToDto(Feedback entidade) {
        if (entidade == null) return null;

        FeedbackRespostaDto.UsuarioFeedbackDto usuarioDto =
                new FeedbackRespostaDto.UsuarioFeedbackDto(
                        entidade.getUsuario().getId(),
                        entidade.getUsuario().getNome()
                );

        FeedbackRespostaDto.ItemPedidoDto itemDto =
                new FeedbackRespostaDto.ItemPedidoDto(
                        entidade.getItemPedido().getId(),
                        entidade.getItemPedido().getProduto()
                );

        return FeedbackRespostaDto.builder()
                .id(entidade.getId())
                .comentario(entidade.getComentario())
                .idPedido(entidade.getPedido().getId())
                .usuario(usuarioDto)
                .itemPedido(itemDto)
                .imagensFeedbacks(entidade.getImagensFeedbacks())
                .build();
    }
}
