package reveste.brecho.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.dto.pedido.PedidoAdicionarProdutoDto;
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.pedido.PedidoMapper;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.dto.pedido.CarrinhoDto;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.service.pedido.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<CarrinhoDto> adicionarProduto(
            @RequestBody @Valid PedidoAdicionarProdutoDto pedidoDto) {

        return ResponseEntity.created(null).body(pedidoService.adicionarProduto(pedidoDto));

    }

    @GetMapping("/{idPedido}/produtos")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosPedido(@PathVariable Integer idPedido) {
        List<ProdutoDTO> produtos = pedidoService.listarProdutos(idPedido);

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<CarrinhoDto> buscarCarrinho(@PathVariable Integer idPedido) {
        List<ProdutoDTO> produtos = pedidoService.listarProdutos(idPedido);
        PedidoDto pedido = pedidoService.buscarPedido(idPedido);
        return ResponseEntity.ok(PedidoMapper.toDetalheCarrinhoDto(pedido, produtos));
    }

    @PutMapping("/{idPedido}/produto/{idProduto}")
    public ResponseEntity<CarrinhoDto> editarQuantidadeProduto(@PathVariable Integer idPedido,
                                                             @PathVariable Integer idProduto,
                                                             @RequestBody Integer quantidadeAtualizada){

        List<ProdutoDTO> produtos = pedidoService.editarQuantidade(idPedido, idProduto, quantidadeAtualizada);
        PedidoDto pedido = pedidoService.buscarPedido(idPedido);
        return ResponseEntity.ok(PedidoMapper.toDetalheCarrinhoDto(pedido, produtos));
    }

    @DeleteMapping("/{idPedido}/produto/{idProduto}")
    public ResponseEntity<Void> removerProduto(@PathVariable Integer idPedido,
                                               @PathVariable Integer idProduto) {
        pedidoService.removerProduto(idPedido, idProduto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> removerProdutos(@PathVariable Integer idPedido) {
        pedidoService.removerProdutos(idPedido);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/em-aberto")
    public ResponseEntity<Void> exportarPedidosEmAberto(){
        pedidoService.exportarPedidosEmAberto();
        return ResponseEntity.ok().build();
    }

}
