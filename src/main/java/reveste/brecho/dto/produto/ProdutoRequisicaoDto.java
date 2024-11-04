package reveste.brecho.dto.produto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.entity.Imagem;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.enun.produto.TamanhoProdutoEnum;

import java.util.List;

@Data @Builder
public class ProdutoRequisicaoDto {

    private Integer id;

    @NotBlank
    private String nome;
    @Enumerated(EnumType.STRING)
    private TamanhoProdutoEnum tamanho;

    @NotBlank
    private String categoria;
    @NotNull
    private Double preco;
    @NotBlank
    private String descricao;
    @NotNull
    private Integer qtdEstoque;
    @Enumerated(EnumType.STRING)
    private StatusProdutoEnum status;
    private List<Imagem> images;

}
