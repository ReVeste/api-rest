package reveste.brecho.dto.feedback;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.entity.ItemPedido;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Usuario;

import java.util.List;

@Data @Builder
public class FeedbackRequisicaoDto {

    @NotBlank
    private String comentario;
    private Integer pedido;
    private Integer usuario;
    private List<String> imagensFeedback;
}
