package reveste.brecho.controller.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import reveste.brecho.dto.pedido.PedidoAdicionarProdutoDto;
import reveste.brecho.dto.pedido.PedidoDto;
import reveste.brecho.dto.produto.ProdutoDTO;
import reveste.brecho.dto.pedido.CarrinhoDto;

import java.util.List;

@RequestMapping("/pedidos")
public interface PedidoSwagger {

    @PostMapping
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto adicionado ao pedido com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Usuário ou Produto não encontrado", content = @Content),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    })
    ResponseEntity<CarrinhoDto> adicionarProduto(@RequestBody @Valid PedidoAdicionarProdutoDto pedidoDto);

    @GetMapping("/{idPedido}/produtos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos encontrados para o pedido", content = @Content),
            @ApiResponse(responseCode = "204", description = "Pedido sem produtos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
    })
    ResponseEntity<List<ProdutoDTO>> listarProdutosPedido(@PathVariable Integer idPedido);

    @GetMapping("/{idPedido}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Carrinho encontrado", content = @Content),
            @ApiResponse(responseCode = "204", description = "Pedido sem produtos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
    })
    ResponseEntity<CarrinhoDto> buscarCarrinho(@PathVariable Integer idPedido);

    @GetMapping("/{idUsuario}/em-aberto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos encontrados para o pedido", content = @Content),
            @ApiResponse(responseCode = "204", description = "Pedido sem produtos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
    })
    ResponseEntity<List<ProdutoDTO>> listarProdutosPedidoEmAberto(@PathVariable Integer idUsuario);

    @PutMapping("/{idPedido}/produto/{idProduto}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Quantidade atualizada com sucesso", content = @Content),
            @ApiResponse(responseCode = "204", description = "Pedido sem produtos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido ou produto não encontrado", content = @Content)
    })
    ResponseEntity<CarrinhoDto> editarQuantidadeProduto(@PathVariable Integer idPedido,
                                                        @PathVariable Integer idProduto,
                                                        @RequestBody Integer quantidadeAtualizada);

    @DeleteMapping("/{idPedido}/produto/{idProduto}")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produto removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pedido ou produto não encontrado", content = @Content)
    })
    ResponseEntity<Void> removerProduto(@PathVariable Integer idPedido,
                                        @PathVariable Integer idProduto);

    @DeleteMapping("/{idPedido}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos removidos com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content)
    })
    ResponseEntity<Void> removerProdutos(@PathVariable Integer idPedido);

    @GetMapping("/em-aberto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedidos exportados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum pedido em andamento encontrado para exportar",
                    content = @Content)
    })
    ResponseEntity<Void> exportarPedidosEmAberto();

    @GetMapping("/{idUsuario}/status")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Pedidos encontrados com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum pedido foi encontrado", content = @Content)
    })
    ResponseEntity<List<PedidoDto>> buscarPorStatus(@PathVariable Integer idUsuario, @RequestParam String status);
}
