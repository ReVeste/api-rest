package reveste.brecho.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.pedido.PedidoMapper;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.dto.pedido.CarrinhoDto;
import reveste.brecho.dto.produto.ProdutoRequisicaoAdicionar;
import reveste.brecho.dto.produto.ProdutoRequisicaoDto;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.service.pedido.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<Pedido> adicionarProduto(
            @RequestBody @Valid ProdutoRequisicaoAdicionar produtoRequisicaoAdicionar) {

        return ResponseEntity.created(null).body(pedidoService.adicionarProduto(
                produtoRequisicaoAdicionar.getProduto(),
                produtoRequisicaoAdicionar.getIdPedido(),
                produtoRequisicaoAdicionar.getQuantidade()));

    }

    @GetMapping("/{idPedido}/produtos")
    public ResponseEntity<List<Produto>> listarProdutosPedido(@PathVariable Integer idPedido) {
        List<Produto> produtos = pedidoService.listarProdutos(idPedido);

        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{idPedido}")
    public ResponseEntity<CarrinhoDto> buscarCarrinho(@PathVariable Integer idPedido) {
        List<Produto> produtos = pedidoService.listarProdutos(idPedido);
        Pedido pedido = pedidoService.buscarPedido(idPedido);
        return ResponseEntity.ok(PedidoMapper.toDetalheCarrinhoDto(pedido, produtos));
    }

    @PutMapping("/{idPedido}/{idProduto}")
    public ResponseEntity<PedidoDto> editarQuantidadeProduto(@PathVariable Integer idPedido,
                                                             @PathVariable Integer idProduto,
                                                             @RequestBody Integer quantidadeAtualizada){
        return ResponseEntity.status(200).body(pedidoService.editarQuantidade(
                idPedido, idProduto, quantidadeAtualizada));
    }

    @DeleteMapping("/{idPedido}/{idProduto}")
    public ResponseEntity<Void> removerProduto(@PathVariable Integer idPedido, @PathVariable Integer idProduto) {
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
