package reveste.brecho.dto.pagamento;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data @Builder
public class ProdutosDto {
    private String produtoId;
    private String produtoNome;
    private String produtoDescricao;
    private Integer produtoQuantidade;
    private BigDecimal produtoPreco;
}
