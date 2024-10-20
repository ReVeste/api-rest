package reveste.brecho.mapper.produto;

import reveste.brecho.dto.produto.ProdutoPedidoDto;
import reveste.brecho.entity.produto.Produto;

public class ProdutoMapper {

    public static ProdutoPedidoDto of(Produto produtoPedido, int quantidade){

        ProdutoPedidoDto produtoPedidoDto = new ProdutoPedidoDto();

        produtoPedidoDto.setIdProduto(produtoPedido.getId());
        produtoPedidoDto.setPrecoProduto(produtoPedido.getPreco());
        produtoPedidoDto.setQuantidade(quantidade);

        return produtoPedidoDto;

    }

}
