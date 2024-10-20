package reveste.brecho.dto.produto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ProdutoPedidoDto {

    private int idProduto;
    private double precoProduto;
    private int quantidade;

}
