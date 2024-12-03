package reveste.brecho.dto.pedido;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.dto.usuario.UsuarioDetalheRespostaDto;
import reveste.brecho.entity.Endereco;
import reveste.brecho.entity.Produto;
import reveste.brecho.entity.Usuario;
import reveste.brecho.enun.pedido.StatusPedidoEnum;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class PedidoPagoDto {

    private Integer id;
    private LocalDateTime dataHora;
    private String tipoFrete;
    private Double valorFrete;
    private Double valorTotal;
    private StatusPedidoEnum status;
    private UsuarioDetalheRespostaDto usuario;
    private List<ProdutoDTO> produtos;
    private Endereco endereco;

}
