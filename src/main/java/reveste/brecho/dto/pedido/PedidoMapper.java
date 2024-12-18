package reveste.brecho.dto.pedido;

import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.dto.usuario.UsuarioMapper;
import reveste.brecho.entity.Endereco;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Usuario;
import reveste.brecho.enun.pedido.StatusPedidoEnum;
import reveste.brecho.exception.NaoEncontradaException;

import java.time.LocalDateTime;
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

    public static Pedido criarPedidoParaUsuario(Usuario usuario) {
        return Pedido.builder()
                .dataHora(LocalDateTime.now())
                .valorTotal(0.0)
                .status(StatusPedidoEnum.EM_ANDAMENTO)
                .usuario(usuario)
                .build();
    }

    public static PedidoPagoDto toDetalhePedidoPagoDto(Pedido pedido, Endereco endereco) {
        return PedidoPagoDto.builder()
                .id(pedido.getId())
                .dataHora(pedido.getDataHora())
                .tipoFrete(pedido.getTipoFrete())
                .valorFrete(pedido.getValorFrete())
                .valorTotal(pedido.getValorTotal())
                .status(pedido.getStatus())
                .usuario(UsuarioMapper.toDetalheDto(pedido.getUsuario()))
                .endereco(endereco)
                .build();
    }

}
