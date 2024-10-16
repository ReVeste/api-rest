package reveste.brecho.service.itempedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.dto.produto.ProdutoMapper;
import reveste.brecho.dto.produto.ProdutoRequisicaoDto;
import reveste.brecho.entity.itempedido.ItemPedido;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.repository.ItemPedidoRepository;

import java.util.List;

@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public List<Produto> buscaProdutoPorPedido(int pedidoId) {

        return itemPedidoRepository.findByPedidoId(pedidoId).stream().map(ItemPedido::getProduto).toList();
    }

    public void removerProdutoPedido(int idPedido, int idProduto) {
        List<Integer> idItemPedido = itemPedidoRepository.findIdByPedidoIdAndProdutoId(idPedido, idProduto);
        itemPedidoRepository.deleteAllById(idItemPedido);
    }

    public PedidoDto editarQuantidadeProduto(int idPedido, int idProduto, int quantidadeAtualizada) {
        ItemPedido itemPedido = itemPedidoRepository.findByPedidoIdAndProdutoId(idPedido, idProduto);
        itemPedido.setQuantidade(quantidadeAtualizada);
        return null;
    }

    public void removerProdutosPorPedido(int idPedido) {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findByPedidoId(idPedido).stream().toList();
        for (ItemPedido itemPedido : itemPedidos) {
            itemPedidoRepository.deleteById(itemPedido.getId());
        }
    }

    public void adicionarProduto(ProdutoRequisicaoDto produtoDto, Pedido pedido, Integer quantidade) {

        Produto produto = ProdutoMapper.requisicaoDtoToProduto(produtoDto);

        Double subTotal = produto.getPreco() * quantidade;

        ItemPedido novoItemPedido = ItemPedido.builder()
                .pedido(pedido)
                .produto(produto)
                .quantidade(quantidade)
                .subTotal(subTotal)
                .build();

        itemPedidoRepository.save(novoItemPedido);
    }

}
