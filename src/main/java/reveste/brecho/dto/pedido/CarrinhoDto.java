package reveste.brecho.dto.pedido;

import lombok.Builder;
import lombok.Data;
import reveste.brecho.dto.produto.ProdutoDTO;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder
public class CarrinhoDto {

    private int id;
    private LocalDateTime dataHora;
    private String tipoFrete;
    private Double valorFrete;
    private Double valorTotal;
    private String status;
    private String nomeUsuario;
    private List<ProdutoDTO> produtos;

}
