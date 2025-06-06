package reveste.brecho.dto.feedback;

import lombok.Builder;
import lombok.Data;
import reveste.brecho.entity.ImagensFeedback;
import reveste.brecho.entity.Produto;

import java.util.List;

@Data @Builder
public class FeedbackRespostaDto {

    private Integer id;
    private String comentario;
    private Integer idPedido;
    private UsuarioFeedbackDto usuario;
    private List<ImagensFeedback> imagensFeedbacks;

    @Data @Builder
    static class UsuarioFeedbackDto {
        private Integer id;
        private String nome;
    }
}
