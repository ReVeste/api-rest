package reveste.brecho.dto.pedido;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PedidoAdicionarProdutoDto {

    private Integer idUsuario;
    private Integer idProduto;
    private Integer quantidadeProduto;

}
