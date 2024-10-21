package reveste.brecho.dto.pedido;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.entity.usuario.Usuario;
import reveste.brecho.enun.pedido.StatusPedidoEnum;

import java.time.LocalDateTime;

@Builder
@Data
public class PedidoDto {

    private Integer id;
    private LocalDateTime dataHora;
    private String tipoFrete;
    private Double valorFrete;
    private Double valorTotal;
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;
    private Usuario usuario;

}
