package reveste.brecho.dto.produto;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.enun.produto.TamanhoProdutoEnum;


@Data @Builder
public class ProdutoDTO {

    private Integer id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TamanhoProdutoEnum tamanho;
    private Integer qualidade;
    private String categoria;
    private Double preco;
    private String descricao;
    private Integer qtdEstoque;
    @Enumerated(EnumType.STRING)
    private StatusProdutoEnum status;

}