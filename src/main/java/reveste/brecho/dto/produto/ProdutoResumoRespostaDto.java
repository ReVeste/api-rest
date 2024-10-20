package reveste.brecho.dto.produto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProdutoResumoRespostaDto {
    private Integer id;
    private String nome;
    private String categoria;
    private Double preco;
    private String urlImagem;
}
