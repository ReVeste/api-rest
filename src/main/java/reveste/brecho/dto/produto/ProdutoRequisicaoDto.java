package reveste.brecho.dto.produto;

import lombok.Builder;
import lombok.Data;
import reveste.brecho.enun.produto.TamanhoEnum;
import reveste.brecho.enun.produto.TipoEnum;

@Data
@Builder
public class ProdutoRequisicaoDto {
    private String nome;
    private TamanhoEnum tamanho;
    private String cor;
    private String categoria;
    private String subCategoria;
    private Double preco;
    private String descricao;
    private String urlImagem;
    private TipoEnum tipo;
}
