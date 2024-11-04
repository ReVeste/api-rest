package reveste.brecho.dto.feedback;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class FeedbackRespostaDto {

    private Integer id;
    private Integer nota;
    private String comentario;
    private UsuarioFeedbackDto usuario;

    @Data @Builder
    static class UsuarioFeedbackDto {
        private Integer id;
        private String nome;
        private String urlFoto;
    }
}
