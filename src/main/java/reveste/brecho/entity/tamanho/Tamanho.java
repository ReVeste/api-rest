package reveste.brecho.entity.tamanho;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import reveste.brecho.enun.produto.TamanhoProdutoEnum;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tamanho {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private TamanhoProdutoEnum tamanho;
    private String categoria;

}
