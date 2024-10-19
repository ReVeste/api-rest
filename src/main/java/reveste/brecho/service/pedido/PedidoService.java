package reveste.brecho.service.pedido;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reveste.brecho.dto.pedido.CarrinhoDto;
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.pedido.PedidoMapper;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.dto.produto.ProdutoRequisicaoDto;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.repository.PedidoRepository;
import reveste.brecho.service.itempedido.ItemPedidoService;
import reveste.brecho.util.Escritor;
import reveste.brecho.util.ListaProduto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ItemPedidoService itemPedidoService;
    private final PedidoRepository pedidoRepository;

    public Pedido adicionarProduto(ProdutoRequisicaoDto produtoDto, Integer idPedido, Integer quantidade) {

        Optional<Pedido> pedido = pedidoRepository.findById(idPedido);
        if (pedido.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
        itemPedidoService.adicionarProduto(produtoDto, pedido.get(), quantidade);

        List<Produto> listaProduto = listarProdutos(idPedido);

        return buscarPedido(idPedido);
    }

    public List<Produto> listarProdutos(int pedidoId) {

        return itemPedidoService.buscaProdutoPorPedido(pedidoId);

    }

    public Pedido buscarPedido(int idPedido) {

        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

        if (pedidoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        return pedidoOpt.get();
    }

    public PedidoDto editarQuantidade(int idPedido, int idProduto, int quantidadeAtualizada) {
        return itemPedidoService.editarQuantidadeProduto(idPedido, idProduto, quantidadeAtualizada);
        // calcularValorTotal()
    }

    public void removerProduto(int idPedido, int idProduto) {
        itemPedidoService.removerProdutoPedido(idPedido, idProduto);
    }

    @Modifying
    @Transactional
    public void removerProdutos(int idPedido) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);
        if (pedidoOpt.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }
        itemPedidoService.removerProdutosPorPedido(idPedido);
        pedidoRepository.atualizarValorTotal(pedidoOpt.get().getId(), 0.0);
    }

    public void exportarPedidosEmAberto() {

        List<Pedido> pedidos = pedidoRepository.findAll();
        List<CarrinhoDto> carrinhoDtos = new ArrayList<>();

        for (int i = 0; i < pedidos.size(); i++) {

            List<Produto> listaProdutos = (listarProdutos(pedidos.get(i).getId()));
            carrinhoDtos.add(PedidoMapper.toDetalheCarrinhoDto(pedidos.get(i), listaProdutos));

        }
        Escritor.exportar(carrinhoDtos);
    }

    /*public static double calcularValorTotal(ListaProduto listaProduto, int index) {
        if (index == listaProduto.size()){
            return 0.0;
        }
        double subTotal = (listaProduto.exibirPorIndex(index).getPrecoProduto()
                * listaProduto.exibirPorIndex(index).getQuantidade());
        index++;
        return subTotal + calcularValorTotal(listaProduto, index);
    }*/

}
