package reveste.brecho.entity.itempedido;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Pedido pedido;

    @ManyToOne
    private Produto produto;

    @NotBlank
    private int quantidade;

    @NotBlank
    private double subTotal;

}