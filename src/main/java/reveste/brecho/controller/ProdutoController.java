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

//    @PostMapping
//    public ResponseEntity<Produto> criar(@RequestBody ProdutoDTO produtoDto){
//
//        Produto novoProduto;
//
//        if (produtoDto.getTipo().equals(TipoEnum.RARA)) {
//            ProdutoEspecial produtoEspecial = new ProdutoEspecial();
//
//            produtoEspecial.setNome(produtoDto.getNome());
//            produtoEspecial.setTamanho(produtoDto.getTamanho());
//            produtoEspecial.setCor(produtoDto.getCor());
//            produtoEspecial.setCategoria(produtoDto.getCategoria());
//            produtoEspecial.setSubCategoria(produtoDto.getSubCategoria());
//            produtoEspecial.setPreco(produtoDto.getPreco());
//            produtoEspecial.setDescricao(produtoDto.getDescricao());
//            produtoEspecial.setUrlImagem(produtoDto.getUrlImagem());
//            produtoEspecial.setTipo(produtoDto.getTipo());
//
//            // Mapeando atributos de ProdutoEspecial
//            produtoEspecial.setAntigoDono(produtoDto.getAntigoDono());
//            produtoEspecial.setHistoria(produtoDto.getHistoria());
//
//            novoProduto = produtoEspecial;
//        } else {
//
//            novoProduto = new Produto(produtoDto);
//
//            novoProduto.setNome(produtoDto.getNome());
//            novoProduto.setTamanho(produtoDto.getTamanho());
//            novoProduto.setCor(produtoDto.getCor());
//            novoProduto.setCategoria(produtoDto.getCategoria());
//            novoProduto.setSubCategoria(produtoDto.getSubCategoria());
//            novoProduto.setPreco(produtoDto.getPreco());
//            novoProduto.setDescricao(produtoDto.getDescricao());
//            novoProduto.setUrlImagem(produtoDto.getUrlImagem());
//            novoProduto.setTipo(produtoDto.getTipo());
//
//        }
//
//        Produto produtoSalvo = produtoService.save(novoProduto);
//        return ResponseEntity.status(201).body(produtoService.save(novoProduto));
//    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Produto> atualizar(@PathVariable int id, @RequestBody ProdutoDTO produtoDto) {
//
//        if (!produtoService.existsById(id)){
//            return ResponseEntity.status(404).build();
//        }
//
//        Produto produtoExistente = produtoService.findById(id).orElse(null);
//
//        if (produtoDto.getTipo().equals(TipoEnum.RARA)) {
//            ProdutoEspecial produtoEspecial;
//
//            if (produtoExistente instanceof ProdutoEspecial) {
//                produtoEspecial = (ProdutoEspecial) produtoExistente;
//            } else {
//                produtoEspecial = new ProdutoEspecial();
//            }
//
//            // Atribuindo os valores comuns de Produto
//            produtoEspecial.setId(id);
//            produtoEspecial.setNome(produtoDto.getNome());
//            produtoEspecial.setTamanho(produtoDto.getTamanho());
//            produtoEspecial.setCor(produtoDto.getCor());
//            produtoEspecial.setCategoria(produtoDto.getCategoria());
//            produtoEspecial.setSubCategoria(produtoDto.getSubCategoria());
//            produtoEspecial.setPreco(produtoDto.getPreco());
//            produtoEspecial.setDescricao(produtoDto.getDescricao());
//            produtoEspecial.setUrlImagem(produtoDto.getUrlImagem());
//            produtoEspecial.setTipo(produtoDto.getTipo());
//
//            // Atribuindo os valores de ProdutoEspecial
//            produtoEspecial.setAntigoDono(produtoDto.getAntigoDono());
//            produtoEspecial.setHistoria(produtoDto.getHistoria());
//
//            return ResponseEntity.status(200).body(produtoService.save(produtoEspecial));
//        }
//
//        produtoExistente.setId(id);
//        produtoExistente.setNome(produtoDto.getNome());
//        produtoExistente.setTamanho(produtoDto.getTamanho());
//        produtoExistente.setCor(produtoDto.getCor());
//        produtoExistente.setCategoria(produtoDto.getCategoria());
//        produtoExistente.setSubCategoria(produtoDto.getSubCategoria());
//        produtoExistente.setPreco(produtoDto.getPreco());
//        produtoExistente.setDescricao(produtoDto.getDescricao());
//        produtoExistente.setUrlImagem(produtoDto.getUrlImagem());
//        produtoExistente.setTipo(produtoDto.getTipo());
//        return ResponseEntity.status(200).body(produtoService.save(produtoExistente));
//    }


}
