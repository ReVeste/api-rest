package reveste.brecho.dto.produto;

import lombok.*;

@Data
@Getter @Setter
@RequiredArgsConstructor
public class ProdutoRequisicaoAdicionar {

    private ProdutoRequisicaoDto produto;
    private Integer idPedido;
    private Integer quantidade;

}
