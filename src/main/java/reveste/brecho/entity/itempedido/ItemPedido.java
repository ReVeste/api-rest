package reveste.brecho.entity.itempedido;

import jakarta.persistence.*;
import lombok.*;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;

@Entity
@Builder
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Produto produto;
    private Integer quantidade;

}
