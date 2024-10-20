package reveste.brecho.entity.pedido;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import reveste.brecho.entity.entrega.Entrega;
import reveste.brecho.entity.usuario.Usuario;
import reveste.brecho.enun.pedido.StatusEnum;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDateTime data;

    @NotNull
    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusEnum status;
    @ManyToOne
    private Usuario usuario;

    // Pedido atualizado

    /*@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    private String tipoFrete;

    @NotNull
    private Double valorFrete;

    @NotNull
    private Double valorTotal;

    @NotNull
    private StatusEnum status;

    @ManyToOne
    private Usuario usuario;

    @OneToOne
    private Entrega entrega;

    */

}
