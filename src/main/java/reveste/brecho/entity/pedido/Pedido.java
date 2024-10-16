package reveste.brecho.entity.pedido;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import reveste.brecho.entity.usuario.Usuario;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private LocalDateTime data;

    @NotNull
    private Double valorTotal;
    @NotBlank
    private StatusEnum status;
    @ManyToOne
    private Usuario usuario;

}
