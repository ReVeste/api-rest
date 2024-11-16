package reveste.brecho.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

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

    public Imagem(Produto produto, String imagemUrl) {
        this.produto = produto;
        this.imagemUrl = imagemUrl;
    }
}
