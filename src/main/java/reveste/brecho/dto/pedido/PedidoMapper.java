package reveste.brecho.dto.pedido;

import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.entity.pedido.Pedido;
import java.util.List;

public class PedidoMapper {

    public static CarrinhoDto toDetalheCarrinhoDto(PedidoDto pedido, List<ProdutoDTO> produtos){
        if (pedido == null) return null;

        return CarrinhoDto.builder()
                .id(pedido.getId())
                .dataHora(pedido.getDataHora())
                .tipoFrete(pedido.getTipoFrete())
                .valorFrete(pedido.getValorFrete())
                .valorTotal(pedido.getValorTotal())
                .status(pedido.getStatus().getDescricao())
                .nomeUsuario(pedido.getUsuario().getNome())
                .produtos(produtos)
                .build();

    }

    public static PedidoDto entidadeToPedidoDto(Pedido pedido){
        if (pedido == null) return null;

        return PedidoDto.builder()
                .id(pedido.getId())
                .dataHora(pedido.getDataHora())
                .tipoFrete(pedido.getTipoFrete())
                .valorFrete(pedido.getValorFrete())
                .valorTotal(pedido.getValorTotal())
                .status(pedido.getStatus())
                .usuario(pedido.getUsuario())
                .build();
    }

}
