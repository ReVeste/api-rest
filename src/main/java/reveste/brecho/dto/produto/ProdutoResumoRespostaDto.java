package reveste.brecho.dto.produto;

import lombok.Builder;
import lombok.Data;
import reveste.brecho.entity.imagem.Imagem;

import java.util.List;

@Data
@Builder
public class ProdutoResumoRespostaDto {
    private Integer id;
    private String nome;
    private String categoria;
    private Double preco;
    private List<Imagem> imagens;
}
