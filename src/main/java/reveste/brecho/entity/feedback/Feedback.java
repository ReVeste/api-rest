package reveste.brecho.entity.feedback;

import jakarta.persistence.*;
import lombok.*;
import reveste.brecho.entity.usuario.Usuario;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer nota;
    private String titulo;
    private String comentario;

    @ManyToOne
    private Usuario usuario;

}
