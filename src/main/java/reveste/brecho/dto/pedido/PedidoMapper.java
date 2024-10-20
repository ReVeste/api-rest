package reveste.brecho.dto.pedido;

import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.entity.usuario.Usuario;
import reveste.brecho.enun.pedido.StatusEnum;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoMapper {

    public static CarrinhoDto toDetalheCarrinhoDto(PedidoDto pedido, List<ProdutoDTO> produtos){
        if (pedido == null) return null;

        return CarrinhoDto.builder()
                .id(pedido.getId())
                .data(pedido.getData())
                .valorTotal(pedido.getValorTotal())
                .status(pedido.getStatus())
                .usuario(pedido.getUsuario().getNome())
                .produtos(produtos)
                .build();

    }

    public static PedidoDto entidadeToPedidoDto(Pedido pedido){
        if (pedido == null) return null;

        return PedidoDto.builder()
                .id(pedido.getId())
                .data(pedido.getData())
                .valorTotal(pedido.getValorTotal())
                .status(pedido.getStatus())
                .usuario(pedido.getUsuario())
                .build();

    }

}
