package reveste.brecho.dto.produto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ProdutoResumoRespostaDto {
    private Integer id;
    private String nome;
    private String categoria;
    private Double preco;
    private List<String> imagens;

}
