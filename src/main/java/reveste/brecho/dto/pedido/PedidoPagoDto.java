package reveste.brecho.dto.pedido;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.entity.Endereco;
import reveste.brecho.entity.Usuario;
import reveste.brecho.enun.pedido.StatusPedidoEnum;

import java.time.LocalDateTime;

@Builder
@Data
public class PedidoPagoDto {

    private Integer id;
    private LocalDateTime dataHora;
    private String tipoFrete;
    private Double valorFrete;
    private Double valorTotal;
    private StatusPedidoEnum status;
    private Usuario usuario;
    private Endereco endereco;

}
