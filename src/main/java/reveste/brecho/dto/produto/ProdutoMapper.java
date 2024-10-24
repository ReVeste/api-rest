package reveste.brecho.dto.produto;

import reveste.brecho.entity.produto.Produto;

public class ProdutoMapper {

    public static ProdutoDetalheRespostaDto toDetalheDto(Produto entidade) {
        if (entidade == null) return null;

        return ProdutoDetalheRespostaDto.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .tamanho(entidade.getTamanho())
                .qualidade(entidade.getQualidade())
                .categoria(entidade.getCategoria())
                .preco(entidade.getPreco())
                .descricao(entidade.getDescricao())
                .qtdEstoque(entidade.getQtdEstoque())
                .status(entidade.getStatus())
                .build();
    }

    public static ProdutoResumoRespostaDto toResumoDto(Produto entidade) {
        if (entidade == null) return null;

        return ProdutoResumoRespostaDto.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .categoria(entidade.getCategoria())
                .preco(entidade.getPreco())
                .build();
    }

    public static Produto requisicaoDtoToProduto(ProdutoRequisicaoDto dto) {
        if (dto == null) return null;

        return Produto.builder()
                .nome(dto.getNome())
                .tamanho(dto.getTamanho())
                .qualidade(dto.getQualidade())
                .categoria(dto.getCategoria())
                .preco(dto.getPreco())
                .descricao(dto.getDescricao())
                .qtdEstoque(dto.getQtdEstoque())
                .status(dto.getStatus())
                .build();
    }

    public static ProdutoDTO entidadeToProdutoDTO(Produto produto, Integer quantidade) {
        if (produto == null || quantidade == null) return null;

        return ProdutoDTO.builder()
                    .id(produto.getId())
                    .nome(produto.getNome())
                    .tamanho(produto.getTamanho())
                    .qualidade(produto.getQualidade())
                    .categoria(produto.getCategoria())
                    .preco(produto.getPreco())
                    .descricao(produto.getDescricao())
                    .qtdEstoque(quantidade)
                    .status(produto.getStatus())
                    .build();
    }

}
