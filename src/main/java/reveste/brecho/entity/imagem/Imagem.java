package reveste.brecho.entity.imagem;

import jakarta.persistence.*;
import lombok.*;
import reveste.brecho.entity.produto.Produto;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Imagem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String imagemUrl;
}
