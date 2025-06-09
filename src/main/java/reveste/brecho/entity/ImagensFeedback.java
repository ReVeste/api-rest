package reveste.brecho.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ImagensFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "feedback_id")
    @JsonBackReference
    private Feedback feedback;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    @JsonBackReference
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference
    private Usuario usuario;

    private String ImagemUrl;

    public ImagensFeedback(Feedback feedback, Pedido pedido, Usuario usuario, String imagemUrl) {
        this.feedback = feedback;
        this.pedido = pedido;
        this.usuario = usuario;
        ImagemUrl = imagemUrl;
    }
}
