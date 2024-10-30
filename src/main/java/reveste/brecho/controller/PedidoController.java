package reveste.brecho.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.dto.pedido.PedidoAdicionarProdutoDto;
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.pedido.PedidoMapper;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.dto.pedido.CarrinhoDto;
import reveste.brecho.dto.usuario.UsuarioDetalheRespostaDto;
import reveste.brecho.dto.produto.ProdutoMapper;
import reveste.brecho.dto.produto.ProdutoResumoRespostaDto;
import reveste.brecho.entity.pedido.Pedido;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.service.pedido.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto adicionado ao pedido com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarrinhoDto.class))),
            @ApiResponse(responseCode = "404", description = "Usuário ou Produto não encontrado",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Requisição inválida",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<CarrinhoDto> adicionarProduto(
            @RequestBody @Valid PedidoAdicionarProdutoDto pedidoDto) {

        return ResponseEntity.created(null).body(pedidoService.adicionarProduto(pedidoDto));

    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos encontrados para o pedido",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoDTO.class))),
            @ApiResponse(responseCode = "204", description = "Pedido sem produtos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content)
    })
    @GetMapping("/{idPedido}/produtos")
    public ResponseEntity<List<ProdutoDTO>> listarProdutosPedido(@PathVariable Integer idPedido) {
        List<ProdutoDTO> produtos = pedidoService.listarProdutos(idPedido);

        return produtos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(produtos);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carrinho encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarrinhoDto.class))),
            @ApiResponse(responseCode = "204", description = "Pedido sem produtos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content)
    })
    @GetMapping("/{idPedido}")
    public ResponseEntity<CarrinhoDto> buscarCarrinho(@PathVariable Integer idPedido) {
        List<ProdutoDTO> produtos = pedidoService.listarProdutos(idPedido);
        PedidoDto pedido = pedidoService.buscarPedido(idPedido);
        return ResponseEntity.ok(PedidoMapper.toDetalheCarrinhoDto(pedido, produtos));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quantidade do produto atualizada com sucesso",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = CarrinhoDto.class))),
            @ApiResponse(responseCode = "204", description = "Pedido sem produtos",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido ou produto não encontrado",
                    content = @Content)
    })
    @PutMapping("/{idPedido}/produto/{idProduto}")
    public ResponseEntity<CarrinhoDto> editarQuantidadeProduto(@PathVariable Integer idPedido,
                                                               @PathVariable Integer idProduto,
                                                               @RequestBody Integer quantidadeAtualizada){

        List<ProdutoDTO> produtos = pedidoService.editarQuantidade(idPedido, idProduto, quantidadeAtualizada);
        PedidoDto pedido = pedidoService.buscarPedido(idPedido);
        return ResponseEntity.ok(PedidoMapper.toDetalheCarrinhoDto(pedido, produtos));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido ou produto não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{idPedido}/produto/{idProduto}")
    public ResponseEntity<Void> removerProduto(@PathVariable Integer idPedido,
                                               @PathVariable Integer idProduto) {
        pedidoService.removerProduto(idPedido, idProduto);
        return ResponseEntity.ok().build();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Todos os produtos removidos com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado",
                    content = @Content)
    })
    @DeleteMapping("/{idPedido}")
    public ResponseEntity<Void> removerProdutos(@PathVariable Integer idPedido) {
        pedidoService.removerProdutos(idPedido);
        return ResponseEntity.ok().build();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedidos exportados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum pedido em andamento encontrado para exportar",
                    content = @Content)
    })
    @GetMapping("/em-aberto")
    public ResponseEntity<Void> exportarPedidosEmAberto(){
        pedidoService.exportarPedidosEmAberto();
        return ResponseEntity.ok().build();
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedidos exportados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum pedido em andamento encontrado para exportar",
                    content = @Content)
    })
    @GetMapping("/status")
    public ResponseEntity<List<PedidoDto>> buscarPorStatus(@RequestParam String status) {
        List<Pedido> pedidos = pedidoService.listarPorStatus(status);

        if (pedidos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(pedidos.stream().map(PedidoMapper::entidadeToPedidoDto).toList());
    }

}
