package reveste.brecho.enun.pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedidoEnum {
    CONCLUIDO("Concluído"),
    EM_ANDAMENTO("Em andamento"),
    CANCELADO("Cancelado");

    private final String descricao;

}
