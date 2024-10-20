package reveste.brecho.entity.produto;

import jakarta.persistence.*;
import lombok.*;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.enun.produto.TamanhoProdutoEnum;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private TamanhoProdutoEnum tamanho; // por enquanto será deixado o enum, porém o tamanho será responsabilidade de outra entidade depois
    private Integer qualidade;
    private String categoria;
    private Double preco;
    private String descricao;
    private Integer qtdEstoque;
    @Enumerated(EnumType.STRING)
    private StatusProdutoEnum status;

}
