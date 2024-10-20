package reveste.brecho.dto.produto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import reveste.brecho.enun.produto.TamanhoEnum;
import reveste.brecho.enun.produto.TipoEnum;

@Getter
@Setter
@Builder
public class ProdutoDTO {

    private Integer id;
    private String nome;
    private TamanhoEnum tamanho;
    private String cor;
    private TipoEnum tipo;
    private String categoria;
    private String subCategoria;
    private Double preco;
    private String descricao;
    private String urlImagem;
    private int quantidade;

}