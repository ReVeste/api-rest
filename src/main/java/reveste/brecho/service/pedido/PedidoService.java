package reveste.brecho.service.pedido;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reveste.brecho.dto.pedido.CarrinhoDto;
import reveste.brecho.dto.pedido.PedidoAdicionarProdutoDto;
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.pedido.PedidoMapper;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.enun.pedido.StatusPedidoEnum;
import reveste.brecho.repository.PedidoRepository;
import reveste.brecho.service.itempedido.ItemPedidoService;
import reveste.brecho.service.usuario.UsuarioService;
import reveste.brecho.util.Escritor;

import java.time.LocalDateTime;
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



            Pedido pedidoCriado = Pedido.builder()
                    .dataHora(LocalDateTime.now())
                    .valorTotal(0.0)
                    .status(StatusPedidoEnum.EM_ANDAMENTO)
                    .usuario(usuarioService.buscarPorId(pedidoDto.getIdUsuario()))
                    .build();

            pedidoRepository.save(pedidoCriado);

            itemPedidoService.adicionarProduto(
                    pedidoCriado,
                    pedidoDto.getIdProduto(),
                    pedidoDto.getQuantidadeProduto());


            return PedidoMapper.toDetalheCarrinhoDto(
                    PedidoMapper.entidadeToPedidoDto(pedidoCriado), listarProdutos(pedidoCriado.getId())
            );
        }

        itemPedidoService.adicionarProduto(
                pedido,
                pedidoDto.getIdProduto(),
                pedidoDto.getQuantidadeProduto());


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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }

        return PedidoMapper.entidadeToPedidoDto(pedidoOpt.get());
    }


    public List<ProdutoDTO> editarQuantidade(int idPedido, int idProduto, int quantidadeAtualizada) {
        return itemPedidoService.editarQuantidadeProduto(idPedido, idProduto, quantidadeAtualizada);
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

        List<Pedido> pedidos = pedidoRepository.findAllByStatus(StatusPedidoEnum.EM_ANDAMENTO);
        List<CarrinhoDto> carrinhoDtos = new ArrayList<>();

        for (int i = 0; i < pedidos.size(); i++) {

            List<ProdutoDTO> listaProdutos = (listarProdutos(pedidos.get(i).getId()));;
            carrinhoDtos.add(PedidoMapper.toDetalheCarrinhoDto(
                    PedidoMapper.entidadeToPedidoDto(pedidos.get(i)), listaProdutos));

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
