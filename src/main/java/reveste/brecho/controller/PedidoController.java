package reveste.brecho.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.controller.swagger.PedidoSwagger;
import reveste.brecho.dto.pedido.*;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.entity.Pedido;
import reveste.brecho.entity.Usuario;
import reveste.brecho.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController implements PedidoSwagger {

    private final PedidoService pedidoService;

    @Override
    public ResponseEntity<CarrinhoDto> adicionarProduto(
            @RequestBody @Valid PedidoAdicionarProdutoDto pedidoDto) {

        return ResponseEntity.created(null).body(pedidoService.adicionarProduto(pedidoDto));

    }

    @Override
    public ResponseEntity<List<ProdutoDTO>> listarProdutosPedido(@PathVariable Integer idPedido) {
        List<ProdutoDTO> produtos = pedidoService.listarProdutos(idPedido);

        return produtos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(produtos);
    }

    @Override
    public ResponseEntity<CarrinhoDto> buscarCarrinho(@PathVariable Integer idPedido) {
        List<ProdutoDTO> produtos = pedidoService.listarProdutos(idPedido);
        PedidoDto pedido = pedidoService.buscarPedido(idPedido);
        return ResponseEntity.ok(PedidoMapper.toDetalheCarrinhoDto(pedido, produtos));
    }

    public ResponseEntity<List<ProdutoDTO>> listarProdutosPedidoEmAberto(@PathVariable Integer idUsuario) {

        Integer idPedidoEmAberto = pedidoService.buscarPedidoEmAberto(idUsuario);
        List<ProdutoDTO> produtos = pedidoService.listarProdutos(idPedidoEmAberto);

        return produtos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(produtos);
    }

    @Override
    public ResponseEntity<CarrinhoDto> editarQuantidadeProduto(@PathVariable Integer idPedido,
                                                               @PathVariable Integer idProduto,
                                                               @RequestBody Integer quantidadeAtualizada){

        List<ProdutoDTO> produtos = pedidoService.editarQuantidade(idPedido, idProduto, quantidadeAtualizada);
        PedidoDto pedido = pedidoService.buscarPedido(idPedido);
        return ResponseEntity.ok(PedidoMapper.toDetalheCarrinhoDto(pedido, produtos));
    }

    @Override
    public ResponseEntity<Void> removerProduto(@PathVariable Integer idPedido,
                                               @PathVariable Integer idProduto) {
        pedidoService.removerProduto(idPedido, idProduto);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> removerProdutos(@PathVariable Integer idPedido) {
        pedidoService.removerProdutos(idPedido);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> exportarPedidosEmAberto(){
        pedidoService.exportarPedidosEmAberto();
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<PedidoDto>> buscarPorStatus(@PathVariable Integer idUsuario, @RequestParam String status) {
        List<Pedido> pedidos = pedidoService.listarPorStatus(idUsuario, status);

        return pedidos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(pedidos.stream().map(PedidoMapper::entidadeToPedidoDto).toList());
    }

    @PutMapping("/{idPedido}")
    public ResponseEntity<Void> atualizarPedidoPago(@PathVariable Integer idPedido) {
        pedidoService.atualizarPedidoPago(idPedido);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/finalizar")
    public ResponseEntity<Void> finalizarPedido() {
        pedidoService.finalizarPedido();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscar-pedido-pago")
    public ResponseEntity<PedidoPagoDto> buscarPedidoEntrega() {

        Pedido pedido = pedidoService.buscarPedidoParaEntrega();
        //Usuario usuario = pedidoService.buscarUsuarioPedidoEntrega(pedido);

        if (pedido == null) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(PedidoMapper.toDetalhePedidoPagoDto(pedido));

    }

}