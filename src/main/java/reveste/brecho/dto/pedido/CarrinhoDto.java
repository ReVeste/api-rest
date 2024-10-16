package reveste.brecho.dto.pedido;

import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.pedido.StatusEnum;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.entity.usuario.Usuario;
import reveste.brecho.enun.produto.TamanhoEnum;
import reveste.brecho.enun.produto.TipoEnum;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder
public class CarrinhoDto {

    private int id;
    private LocalDateTime data;
    private double valorTotal;
    private StatusEnum status;
    private String usuario;
    private List<Produto> produtos;



}
