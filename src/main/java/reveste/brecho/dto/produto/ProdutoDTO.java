package reveste.brecho.dto.produto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.enun.produto.TamanhoProdutoEnum;

import java.util.List;


@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Integer id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TamanhoProdutoEnum tamanho;
    private String categoria;
    private String marca;
    private Double preco;
    private String descricao;
    private Integer qtdEstoque;
    @Enumerated(EnumType.STRING)
    private StatusProdutoEnum status;
    private List<String> imagens;

}