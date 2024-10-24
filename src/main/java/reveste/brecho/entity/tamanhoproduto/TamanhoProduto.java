package reveste.brecho.entity.tamanhoproduto;

import jakarta.persistence.*;
import lombok.*;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.entity.tamanho.Tamanho;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TamanhoProduto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne private Produto produto;
    @ManyToOne private Tamanho tamanho;
    private Integer estoque;

}
