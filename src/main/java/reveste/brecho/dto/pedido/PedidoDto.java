package reveste.brecho.dto.pedido;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.entity.usuario.Usuario;
import reveste.brecho.enun.pedido.StatusEnum;

import java.time.LocalDateTime;

@Builder
@Data
public class PedidoDto {

    private int id;
    private LocalDateTime data;
    private Double valorTotal;
    private StatusEnum status;
    private Usuario usuario;

}
