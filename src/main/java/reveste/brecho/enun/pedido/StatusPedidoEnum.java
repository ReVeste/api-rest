package reveste.brecho.enun.pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusPedidoEnum {
    CONCLUIDO("Concluído"),
    PAGO("Pagamento Efetuado"),
    EM_ANDAMENTO("Em andamento"),
    CANCELADO("Cancelado"),
    AVALIADO("Avaliado");

    private final String descricao;

}
