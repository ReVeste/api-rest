package reveste.brecho.dto.pedido;

import lombok.Builder;
import lombok.Data;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.enun.pedido.StatusEnum;
import reveste.brecho.entity.produto.Produto;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder
public class CarrinhoDto {

    private int id;
    private LocalDateTime data;
    private double valorTotal;
    private StatusEnum status;
    private String usuario;
    private List<ProdutoDTO> produtos;

}
