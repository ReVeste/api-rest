package reveste.brecho.entity.feedback;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import reveste.brecho.entity.usuario.Usuario;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer nota;
    @NotNull
    private String titulo;

    private String comentario;

    @ManyToOne
    private Usuario usuario;

}
