package reveste.brecho.dto.itempedido;

import reveste.brecho.entity.ItemPedido;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Produto;
import reveste.brecho.exception.ArgumentoInvalidoException;
import reveste.brecho.exception.NaoEncontradaException;

public class ItemPedidoMapper {

    public static ItemPedido criarItemPedido(Pedido pedido, Produto produto, Integer quantidade) {
        if (pedido == null) throw new ArgumentoInvalidoException("ItemPedido", "pedido");
        if (quantidade == null || quantidade <= 0) throw new ArgumentoInvalidoException("ItemPedido", "quantidade");
        if (produto == null) throw new NaoEncontradaException("Produto");

        return ItemPedido.builder()
                .pedido(pedido)
                .produto(produto)
                .quantidade(quantidade)
                .build();
    }

//    public static ItemPedido criarItemPedido(Pedido pedido, Produto produto, Integer quantidade) {
//
//        return ItemPedido.builder()
//                .pedido(pedido)
//                .produto(produto)
//                .quantidade(quantidade)
//                .build();
//    }
}
