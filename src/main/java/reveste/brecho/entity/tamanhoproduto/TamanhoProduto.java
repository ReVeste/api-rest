package reveste.brecho.entity.tamanhoproduto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.entity.tamanho.Tamanho;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TamanhoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Tamanho tamanho;

    @NotNull
    private Integer estoque;

}
