package reveste.brecho.service.itempedido;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.dto.produto.ProdutoMapper;
import reveste.brecho.entity.itempedido.ItemPedido;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.repository.ItemPedidoRepository;
import reveste.brecho.service.produto.ProdutoService;
import reveste.brecho.util.Ordenador;
import reveste.brecho.util.PesquisaBinaria;

import java.util.ArrayList;
import java.util.List;


@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    public void adicionarProduto(Pedido pedido, Integer idProduto, Integer quantidade) {

        Produto produto = produtoService.buscarPorId(idProduto);

        ItemPedido itemPedidoCriado = ItemPedido.builder()
                .pedido(pedido)
                .produto(produto)
                .quantidade(quantidade)
                .build();

        itemPedidoRepository.save(itemPedidoCriado);

    }

    public List<ProdutoDTO> buscaProdutoPorPedido(int pedidoId) {

        List<ItemPedido> produtosDoPedido = itemPedidoRepository.findByPedidoId(pedidoId);

        List<ProdutoDTO> produtosDto = new ArrayList<>();

        for (int i = 0; i < produtosDoPedido.size(); i++) {
            produtosDto.add(ProdutoMapper.entidadeToProdutoDTO(
                    produtosDoPedido.get(i).getProduto(), produtosDoPedido.get(i).getQuantidade()));
        }

        return produtosDto;
    }

    @Modifying
    @Transactional
    public List<ProdutoDTO> editarQuantidadeProduto(int idPedido, int idProduto, int quantidadeAtualizada) {
        List<ItemPedido> produtosDoPedido = itemPedidoRepository.findByPedidoId(idPedido);
        if (produtosDoPedido.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produtos não encontrados");
        }
        produtosDoPedido = Ordenador.ordenarItemPedidoPorProduto(produtosDoPedido);
        Integer idItemPedidoParaEditar = PesquisaBinaria.buscarItemProdutoPorProduto(produtosDoPedido, idProduto);

        itemPedidoRepository.atualizarQuantidade(
                produtosDoPedido.get(idItemPedidoParaEditar).getId(), quantidadeAtualizada);

        List<ProdutoDTO> produtosDto = new ArrayList<>();

        for (int i = 0; i < produtosDoPedido.size(); i++) {
            produtosDto.add(ProdutoMapper.entidadeToProdutoDTO(
                    produtosDoPedido.get(i).getProduto(), produtosDoPedido.get(i).getQuantidade()));
        }

        return produtosDto;

    }

    public void removerProdutoPedido(int idPedido, int idProduto) {
        List<ItemPedido> produtosDoPedido = itemPedidoRepository.findByPedidoId(idPedido);
        produtosDoPedido = Ordenador.ordenarItemPedidoPorProduto(produtosDoPedido);
        Integer idItemPedidoParaDeletar = PesquisaBinaria.buscarItemProdutoPorProduto(produtosDoPedido, idProduto);
        itemPedidoRepository.deleteById(produtosDoPedido.get(idItemPedidoParaDeletar).getId());
    }

    public void removerProdutosPorPedido(int idPedido) {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findByPedidoId(idPedido).stream().toList();
        for (ItemPedido itemPedido : itemPedidos) {
            itemPedidoRepository.deleteById(itemPedido.getId());
        }
    }

}
