package reveste.brecho.controller.swagger;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.dto.produto.ProdutoDetalheRespostaDto;
import reveste.brecho.dto.produto.ProdutoRequisicaoDto;
import reveste.brecho.dto.produto.ProdutoResumoRespostaDto;
import reveste.brecho.enun.produto.CategoriaEnum;

import java.util.List;

public interface ProdutoSwagger {

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<ProdutoDetalheRespostaDto> buscarPorId(@PathVariable int id);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso", content = @Content),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })
    @GetMapping
    ResponseEntity<List<ProdutoResumoRespostaDto>> listar();

    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto criado com sucesso", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<ProdutoDetalheRespostaDto> criar(@RequestBody @Valid ProdutoRequisicaoDto produtoDTO);

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<ProdutoDetalheRespostaDto> atualizarPorId(@PathVariable int id,
                                                                    @RequestBody @Valid ProdutoRequisicaoDto produtoDto);

    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso", content = @Content),
            @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Void> deletar(@PathVariable int id);

    @GetMapping("/categoria")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Produtos encontrados com sucesso", content = @Content),
            @ApiResponse(responseCode = "204", description = "Nenhum produto encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content)
    })
    ResponseEntity<List<ProdutoResumoRespostaDto>> buscarPorCategoria(@RequestParam CategoriaEnum categoria);
}
