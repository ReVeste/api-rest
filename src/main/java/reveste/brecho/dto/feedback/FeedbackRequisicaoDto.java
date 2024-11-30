package reveste.brecho.dto.feedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data @Builder
public class FeedbackRequisicaoDto {

    private Integer id;
    @NotNull
    private Integer nota;
    @NotBlank
    private String comentario;
    @NotNull
    private Integer idUsuario;
}
