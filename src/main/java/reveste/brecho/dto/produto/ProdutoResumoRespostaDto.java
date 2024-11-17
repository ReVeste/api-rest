package reveste.brecho.dto.produto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.enun.produto.CategoriaEnum;
import reveste.brecho.enun.produto.StatusProdutoEnum;

import java.util.List;

@Data
@Builder
public class ProdutoResumoRespostaDto {
    private Integer id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;
    private Double preco;
    private List<String> imagens;
    @Enumerated(EnumType.STRING)
    private StatusProdutoEnum status;

}
