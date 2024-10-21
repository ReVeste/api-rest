package reveste.brecho.controller;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.dto.produto.ProdutoRequisicaoDto;
import reveste.brecho.dto.produto.ProdutoDetalheRespostaDto;
import reveste.brecho.dto.produto.ProdutoMapper;
import reveste.brecho.dto.produto.ProdutoResumoRespostaDto;
import reveste.brecho.entity.produto.Produto;
import reveste.brecho.service.produto.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProdutoDetalheRespostaDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDetalheRespostaDto> buscarPorId(@PathVariable int id){
        return ResponseEntity.ok(ProdutoMapper.toDetalheDto(produtoService.buscarPorId(id)));
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProdutoResumoRespostaDto.class)) }),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })@GetMapping
    public ResponseEntity<List<ProdutoResumoRespostaDto>> listar() {
        List<Produto> produtos = produtoService.listar();

        if (produtos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(produtos.stream().map(ProdutoMapper::toResumoDto).toList());
    }


    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProdutoDetalheRespostaDto.class)) }),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })    @PostMapping
    public ResponseEntity<ProdutoDetalheRespostaDto> criar(@RequestBody ProdutoRequisicaoDto produtoDTO){
        Produto produtoCriado = produtoService.criar(ProdutoMapper.requisicaoDtoToProduto(produtoDTO));
        return ResponseEntity.created(null).body(ProdutoMapper.toDetalheDto(produtoCriado));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ProdutoDetalheRespostaDto.class)) }),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDetalheRespostaDto> atualizarPorId(@PathVariable int id, @RequestBody ProdutoRequisicaoDto produtoDto){
        Produto produtoAtualizado = produtoService.atualizar(id, ProdutoMapper.requisicaoDtoToProduto(produtoDto));
        return ResponseEntity.ok(ProdutoMapper.toDetalheDto(produtoAtualizado));
    }

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/categoria")
    public ResponseEntity<List<ProdutoResumoRespostaDto>> buscarPorCategoria(@RequestParam String categoria) {
        List<Produto> produtos = produtoService.listarPorCategoria(categoria);

        if (produtos.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(produtos.stream().map(ProdutoMapper::toResumoDto).toList());
    }

}
