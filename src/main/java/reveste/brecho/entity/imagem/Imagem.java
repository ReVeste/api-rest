package reveste.brecho.entity.imagem;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @ManyToOne @JoinColumn(name = "produto_id")
    @JsonBackReference
    private Produto produto;
    private String imagemUrl;
}
