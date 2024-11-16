package reveste.brecho.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reveste.brecho.controller.swagger.ProdutoSwagger;
import reveste.brecho.dto.produto.ProdutoRequisicaoDto;
import reveste.brecho.dto.produto.ProdutoDetalheRespostaDto;
import reveste.brecho.dto.produto.ProdutoMapper;
import reveste.brecho.dto.produto.ProdutoResumoRespostaDto;
import reveste.brecho.entity.Produto;
import reveste.brecho.enun.produto.CategoriaEnum;
import reveste.brecho.service.ProdutoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController implements ProdutoSwagger {

    private final ProdutoService produtoService;

    @Override
    public ResponseEntity<ProdutoDetalheRespostaDto> buscarPorId(@PathVariable int id){
        return ResponseEntity.ok(ProdutoMapper.toDetalheDto(produtoService.buscarPorId(id)));
    }

    @Override
    public ResponseEntity<List<ProdutoResumoRespostaDto>> listar() {
        List<Produto> produtos = produtoService.listar();

        return produtos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(produtos.stream().map(ProdutoMapper::toResumoDto).toList());
    }

    @Override
    public ResponseEntity<ProdutoDetalheRespostaDto> criar(@RequestBody @Valid ProdutoRequisicaoDto produtoDTO){
        Produto produtoCriado = produtoService.criar(ProdutoMapper.criacaoDtoToProduto(produtoDTO));
        return ResponseEntity.created(null).body(ProdutoMapper.toDetalheDto(produtoCriado));
    }

    @Override
    public ResponseEntity<ProdutoDetalheRespostaDto> atualizarPorId(@PathVariable int id,
                                                                    @RequestBody @Valid ProdutoRequisicaoDto produtoDto){


        Produto produtoAtualizado = produtoService.atualizar(id, ProdutoMapper.atualizacaoDtoToProduto(produtoDto));
        return ResponseEntity.ok(ProdutoMapper.toDetalheDto(produtoAtualizado));
    }

    @Override
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        produtoService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ProdutoResumoRespostaDto>> buscarPorCategoria(@RequestParam CategoriaEnum categoria) {
        List<Produto> produtos = produtoService.listarPorCategoria(categoria);

        return produtos.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(produtos.stream().map(ProdutoMapper::toResumoDto).toList());
    }

}
