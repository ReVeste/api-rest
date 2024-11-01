package reveste.brecho.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import reveste.brecho.dto.pedido.CarrinhoDto;
import reveste.brecho.dto.pedido.PedidoAdicionarProdutoDto;
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.pedido.PedidoMapper;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Usuario;
import reveste.brecho.enun.pedido.StatusPedidoEnum;
import reveste.brecho.exception.NaoEncontradaException;
import reveste.brecho.repository.PedidoRepository;
import reveste.brecho.service.usuario.UsuarioService;
import reveste.brecho.util.Escritor;
import reveste.brecho.util.listaProduto.ListaProduto;
import reveste.brecho.util.listaProduto.ListaProdutoMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PedidoService {

    private final ItemPedidoService itemPedidoService;
    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;

    @Modifying
    @Transactional
    public CarrinhoDto adicionarProduto(PedidoAdicionarProdutoDto pedidoDto) {

        Pedido pedido = pedidoRepository.findByUsuarioIdAndStatus(pedidoDto.getIdUsuario(), StatusPedidoEnum.EM_ANDAMENTO);

        if (pedido == null) {
            Usuario usuario = usuarioService.buscarPorId(pedidoDto.getIdUsuario());

            Pedido pedidoCriado = PedidoMapper.criarPedidoParaUsuario(usuario);
            pedidoRepository.save(pedidoCriado);

            itemPedidoService.adicionarProduto(
                    pedidoCriado,
                    pedidoDto.getIdProduto(),
                    pedidoDto.getQuantidadeProduto());

            ListaProduto listaProduto = ListaProdutoMapper.toListaProduto(listarProdutos(pedidoCriado.getId()));
            pedidoRepository.atualizarValorTotal(pedidoCriado.getId(), calcularValorTotal(listaProduto, 0));

            return PedidoMapper.toDetalheCarrinhoDto(
                    PedidoMapper.entidadeToPedidoDto(pedidoCriado), listarProdutos(pedidoCriado.getId())
            );
        }

        itemPedidoService.adicionarProduto(
                pedido,
                pedidoDto.getIdProduto(),
                pedidoDto.getQuantidadeProduto());

        ListaProduto listaProduto = ListaProdutoMapper.toListaProduto(listarProdutos(pedido.getId()));
        pedidoRepository.atualizarValorTotal(pedido.getId(), calcularValorTotal(listaProduto, 0));

        return PedidoMapper.toDetalheCarrinhoDto(
                PedidoMapper.entidadeToPedidoDto(pedido), listarProdutos(pedido.getId())
        );
    }

    public List<ProdutoDTO> listarProdutos(int pedidoId) {
        return itemPedidoService.buscaProdutoPorPedido(pedidoId);
    }

    public PedidoDto buscarPedido(int idPedido) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

        if (pedidoOpt.isEmpty()) {
            throw new NaoEncontradaException("Usuário");
        }

        return PedidoMapper.entidadeToPedidoDto(pedidoOpt.get());
    }


    public List<ProdutoDTO> editarQuantidade(int idPedido, int idProduto, int quantidadeAtualizada) {
        ListaProduto listaProduto = ListaProdutoMapper.toListaProduto(listarProdutos(idPedido));
        pedidoRepository.atualizarValorTotal(idPedido, calcularValorTotal(listaProduto, 0));

        return itemPedidoService.editarQuantidadeProduto(idPedido, idProduto, quantidadeAtualizada);
    }

    public void removerProduto(int idPedido, int idProduto) {
        itemPedidoService.removerProdutoPedido(idPedido, idProduto);

        ListaProduto listaProduto = ListaProdutoMapper.toListaProduto(listarProdutos(idPedido));
        pedidoRepository.atualizarValorTotal(idPedido, calcularValorTotal(listaProduto, 0));
    }

    @Modifying
    @Transactional
    public void removerProdutos(int idPedido) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(idPedido);

        if (pedidoOpt.isEmpty()){
            throw new NaoEncontradaException("Pedido");
        }

        itemPedidoService.removerProdutosPorPedido(idPedido);
        pedidoRepository.atualizarValorTotal(pedidoOpt.get().getId(), 0.0);
    }

    public void exportarPedidosEmAberto() {

        List<Pedido> pedidos = pedidoRepository.findAllByStatus(StatusPedidoEnum.EM_ANDAMENTO);
        List<CarrinhoDto> carrinhoDtos = new ArrayList<>();

        if (pedidos.isEmpty()) throw new NaoEncontradaException("Pedido");

        for (Pedido pedido : pedidos) {
            List<ProdutoDTO> listaProdutos = listarProdutos(pedido.getId());
            carrinhoDtos.add(PedidoMapper.toDetalheCarrinhoDto(
                    PedidoMapper.entidadeToPedidoDto(pedido), listaProdutos));
        }

        Escritor.exportar(carrinhoDtos);
    }

    public static double calcularValorTotal(ListaProduto listaProduto, int index) {
            if (index == listaProduto.size()){
                return 0.0;
            }
            double subTotal = (listaProduto.exibirPorIndex(index).getQtdEstoque()
                    * listaProduto.exibirPorIndex(index).getQtdEstoque());
            index++;
            return subTotal + calcularValorTotal(listaProduto, index);
    }

    public List<Pedido> listarPorStatus(String status) {
        return status.isEmpty()
                ? pedidoRepository.findAll()
                : pedidoRepository.findAllByStatus(StatusPedidoEnum.valueOf(status));
    }
}
