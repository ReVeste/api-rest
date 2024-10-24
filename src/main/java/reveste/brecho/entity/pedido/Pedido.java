package reveste.brecho.entity.pedido;

import jakarta.persistence.*;
import lombok.*;
import reveste.brecho.entity.usuario.Usuario;
import reveste.brecho.enun.pedido.StatusPedidoEnum;
//import reveste.brecho.entity.entrega.Entrega;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime dataHora;
    private String tipoFrete;
    private Double valorFrete;
    private Double valorTotal;
    @Enumerated(EnumType.STRING)
    private StatusPedidoEnum status;
    @ManyToOne
    private Usuario usuario;
//    @OneToOne
//    private Entrega entrega;







}
