package reveste.brecho.dto.produto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.enun.produto.TamanhoProdutoEnum;

import java.util.List;

@Data @Builder
public class ProdutoDetalheRespostaDto {
    private Integer id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TamanhoProdutoEnum tamanho;
    private String marca;
    private String categoria;
    private Double preco;
    private String descricao;
    private Integer qtdEstoque;
    @Enumerated(EnumType.STRING)
    private StatusProdutoEnum status;
    private List<String> imagens;

}
