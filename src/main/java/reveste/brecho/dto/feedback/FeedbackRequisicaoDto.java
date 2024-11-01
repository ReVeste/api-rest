package reveste.brecho.dto.feedback;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class FeedbackRequisicaoDto {
    private Integer id;
    private Integer nota;
    private String comentario;
    private Integer idUsuario;
}
