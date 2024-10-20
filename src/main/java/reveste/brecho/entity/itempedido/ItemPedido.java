package reveste.brecho.entity.itempedido;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    @NotNull
    private int quantidade;

    @NotNull
    private double subTotal;

}
