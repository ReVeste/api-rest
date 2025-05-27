package reveste.brecho.dto.produto;

import reveste.brecho.entity.Imagem;
import reveste.brecho.entity.Produto;
import reveste.brecho.exception.ArgumentoInvalidoException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class ProdutoMapper {

    public static ProdutoDetalheRespostaDto toDetalheDto(Produto entidade) {
        if (entidade == null) return null;

        return ProdutoDetalheRespostaDto.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .tamanho(entidade.getTamanho())
                .marca(entidade.getMarca())
                .categoria(entidade.getCategoria())
                .preco(entidade.getPreco())
                .descricao(entidade.getDescricao())
                .qtdEstoque(entidade.getQtdEstoque())
                .status(entidade.getStatus())
                .imagens(entidade.getImagens() != null && !entidade.getImagens().isEmpty()
                        ? entidade.getImagens().stream()
                        .map(Imagem::getImagemUrl)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .build();
    }

    public static ProdutoResumoRespostaDto toResumoDto(Produto entidade) {
        if (entidade == null) return null;

        return ProdutoResumoRespostaDto.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .tamanho(entidade.getTamanho())
                .categoria(entidade.getCategoria())
                .preco(entidade.getPreco())
                .imagens(entidade.getImagens() != null && !entidade.getImagens().isEmpty()
                        ? entidade.getImagens().stream()
                        .map(Imagem::getImagemUrl)
                        .collect(Collectors.toList())
                        : Collections.emptyList())
                .status(entidade.getStatus())
                .build();
    }

    public static Produto criacaoDtoToProduto(ProdutoRequisicaoDto dto) {
        if (dto == null) return null;

        Produto produto = Produto.builder()
                .nome(dto.getNome())
                .tamanho(dto.getTamanho())
                .marca(dto.getMarca())
                .categoria(dto.getCategoria())
                .preco(dto.getPreco())
                .descricao(dto.getDescricao())
                .qtdEstoque(dto.getQtdEstoque())
                .status(dto.getStatus())
                .dataCadastro(LocalDate.now())

                .build();

        List<Imagem> imagens = dto.getImages().stream()
                .map(url -> new Imagem(produto, url))
                .collect(Collectors.toList());

        produto.setImagens(imagens);


        return produto;
    }

    public static Produto atualizacaoDtoToProduto(ProdutoRequisicaoDto dto) {
        if (dto == null) return null;

        Produto produto = Produto.builder()
                .id(dto.getId())
                .nome(dto.getNome())
                .tamanho(dto.getTamanho())
                .marca(dto.getMarca())
                .categoria(dto.getCategoria())
                .preco(dto.getPreco())
                .descricao(dto.getDescricao())
                .qtdEstoque(dto.getQtdEstoque())
                .status(dto.getStatus())
                .build();

        List<Imagem> imagens = dto.getImages().stream()
                .map(url -> new Imagem(produto, url))
                .collect(Collectors.toList());

        produto.setImagens(imagens);

        return produto;
    }

    public static ProdutoDTO entidadeToProdutoDTO(Produto produto, Integer quantidade, Integer idPedido) {
        if (produto == null || quantidade == null) return null;

        return ProdutoDTO.builder()
                .id(produto.getId())
                .nome(produto.getNome())
                .tamanho(produto.getTamanho())
                .marca(produto.getMarca())
                .categoria(produto.getCategoria())
                .preco(produto.getPreco())
                .descricao(produto.getDescricao())
                .qtdEstoque(quantidade)
                .status(produto.getStatus())
                .idPedido(idPedido)
                .imagens(produto.getImagens().stream()
                        .map(Imagem::getImagemUrl)
                        .collect(Collectors.toList()))
                .build();
    }

}