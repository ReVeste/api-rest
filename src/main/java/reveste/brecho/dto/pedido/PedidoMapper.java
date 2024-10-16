package reveste.brecho.dto.pedido;

import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;

import java.util.List;

public class PedidoMapper {

    public static CarrinhoDto toDetalheCarrinhoDto(Pedido pedido, List<Produto> produtos){
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

}
