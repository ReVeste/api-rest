package reveste.brecho.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import reveste.brecho.enun.produto.CategoriaEnum;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.enun.produto.TamanhoProdutoEnum;

import java.util.List;


@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String marca;
    private String descricao;
    private Double preco;
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;
    private Integer qtdEstoque;
    @Enumerated(EnumType.STRING)
    private TamanhoProdutoEnum tamanho;
    @Enumerated(EnumType.STRING)
    private StatusProdutoEnum status;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Imagem> imagens;

}
