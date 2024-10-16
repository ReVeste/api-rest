package reveste.brecho.entity.produto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.enun.produto.TamanhoEnum;
import reveste.brecho.enun.produto.TipoEnum;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    // Produto atualizado

    /* @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    private String nome;

    @NotBlank
    private TamanhoEnum tamanho;

    @NotBlank
    private Integer qualidade;

    @NotBlank
    private String categoria;

    @NotBlank
    private Double preco;

    @NotBlank
    private String descricao;

    @NotBlank
    private Integer quantidadeEstoque;

    @NotBlank
    private StatusProdutoEnum status;

    */

    @Override
    public String toString() {
        return "Produto{" +
                ", nome='" + nome + '\'' +
                ", tamanho=" + tamanho +
                ", cor='" + cor + '\'' +
                ", tipo=" + tipo +
                ", categoria='" + categoria + '\'' +
                ", subCategoria='" + subCategoria + '\'' +
                ", preco=" + preco +
                ", descricao='" + descricao + '\''+
                '}';
    }

}
