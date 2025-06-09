package reveste.brecho.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reveste.brecho.dto.itempedido.ItemPedidoMapper;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.dto.produto.ProdutoMapper;
import reveste.brecho.entity.ItemPedido;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Produto;
import reveste.brecho.enun.produto.StatusProdutoEnum;
import reveste.brecho.exception.ArgumentoInvalidoException;
import reveste.brecho.exception.ConflitoException;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.ItemPedidoRepository;
import reveste.brecho.util.Ordenador;
import reveste.brecho.util.PesquisaBinaria;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ItemPedidoService {

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    public void adicionarProduto(Pedido pedido, Integer idProduto, Integer quantidade) {
        if (itemPedidoRepository.existsByProdutoAndPedido(pedido.getId(), idProduto)) {
            throw new ConflitoException("Pedido");
        }

        Produto produto = produtoService.buscarPorId(idProduto);

        ItemPedido itemPedidoParaCriar = ItemPedidoMapper.criarItemPedido(pedido, produto, quantidade);
        itemPedidoRepository.save(itemPedidoParaCriar);
    }

    public List<ProdutoDTO> buscaProdutoPorPedido(int pedidoId) {
        List<ItemPedido> produtosDoPedido = itemPedidoRepository.findByPedidoId(pedidoId);
//        if (produtosDoPedido.isEmpty()) throw new NaoEncontradaException("Item Pedido");

        return produtosDoPedido.stream().map(itemPedido -> ProdutoMapper.entidadeToProdutoDTO(
                itemPedido.getProduto(), itemPedido.getQuantidade(), pedidoId)).collect(Collectors.toList());
    }

    @Modifying
    @Transactional
    public List<ProdutoDTO> editarQuantidadeProduto(int idPedido, int idProduto, int quantidadeAtualizada) {
        List<ItemPedido> produtosDoPedido = itemPedidoRepository.findByPedidoId(idPedido);
        if (produtosDoPedido.isEmpty()) throw new NaoEncontradaException("Produto");

        produtosDoPedido = Ordenador.ordenarItemPedidoPorProduto(produtosDoPedido);
        Integer idItemPedidoParaEditar = PesquisaBinaria.buscarItemProdutoPorProduto(produtosDoPedido, idProduto);

        if (idItemPedidoParaEditar == -1) throw new NaoEncontradaException("Produto", idProduto);

        itemPedidoRepository.atualizarQuantidade(
                produtosDoPedido.get(idItemPedidoParaEditar).getId(), quantidadeAtualizada);

        return produtosDoPedido.stream().map(itemPedido -> ProdutoMapper.entidadeToProdutoDTO(
                itemPedido.getProduto(), itemPedido.getQuantidade(), idPedido)).collect(Collectors.toList());
    }

    public void removerProdutoPedido(int idPedido, int idProduto) {
        List<ItemPedido> produtosDoPedido = itemPedidoRepository.findByPedidoId(idPedido);
        if (produtosDoPedido.isEmpty()) throw new NaoEncontradaException("Pedido", idPedido);

        produtosDoPedido = Ordenador.ordenarItemPedidoPorProduto(produtosDoPedido);
        Integer idItemPedidoParaDeletar = PesquisaBinaria.buscarItemProdutoPorProduto(produtosDoPedido, idProduto);
        if (idItemPedidoParaDeletar == -1) throw new NaoEncontradaException("Produto", idProduto);

        itemPedidoRepository.deleteById(produtosDoPedido.get(idItemPedidoParaDeletar).getId());
    }

    public void removerProdutosPorPedido(int idPedido) {
        List<ItemPedido> itemPedidos = itemPedidoRepository.findByPedidoId(idPedido).stream().toList();
        if (itemPedidos.isEmpty()) throw new NaoEncontradaException("Pedido", idPedido);

        for (ItemPedido itemPedido : itemPedidos) {
            try {
                itemPedidoRepository.deleteById(itemPedido.getId());
            } catch (DataAccessException e) {
                throw new RuntimeException("Erro ao remover produto: " + e.getMessage(), e);
            }
        }
    }

    public List<ProdutoDTO> finalizarPedido(int idPedido) {

        List<ProdutoDTO> listaProdutos = buscaProdutoPorPedido(idPedido);
        List<Integer> listaId = new ArrayList<>();

        for (ProdutoDTO listaProduto : listaProdutos) {
            listaId.add(listaProduto.getId());
        }

        produtoService.finalizarPedido(listaId);

        return listaProdutos;
    }

    public List<Produto> buscarProdutosRelacionados(List<Pedido> pedidos) {

        return itemPedidoRepository.buscarProdutosRelacionados(pedidos);

    }

    public ItemPedido buscarItemPedido(Integer idItemPedidos) {

        Optional<ItemPedido> optItemPedido = itemPedidoRepository.findById(idItemPedidos);

        if (optItemPedido.isEmpty()) throw new NaoEncontradaException("Item Pedido");

        return optItemPedido.get();

    }
}
