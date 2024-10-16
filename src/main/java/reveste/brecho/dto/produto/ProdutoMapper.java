package reveste.brecho.dto.produto;

import reveste.brecho.entity.produto.Produto;

public class ProdutoMapper {

    public static ProdutoDetalheRespostaDto toDetalheDto(Produto entidade) {
        if (entidade == null) return null;

        return ProdutoDetalheRespostaDto.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .tamanho(entidade.getTamanho())
                .categoria(entidade.getCategoria())
                .subCategoria(entidade.getSubCategoria())
                .preco(entidade.getPreco())
                .descricao(entidade.getDescricao())
                .urlImagem(entidade.getUrlImagem())
                .tipo(entidade.getTipo())
                .build();
    }

    public static ProdutoResumoRespostaDto toResumoDto(Produto entidade) {
        if (entidade == null) return null;

        return ProdutoResumoRespostaDto.builder()
                .id(entidade.getId())
                .nome(entidade.getNome())
                .categoria(entidade.getCategoria())
                .preco(entidade.getPreco())
                .urlImagem(entidade.getUrlImagem())
                .build();
    }

    public static Produto requisicaoDtoToProduto(ProdutoRequisicaoDto dto) {
        if (dto == null) return null;

        return Produto.builder()
                .nome(dto.getNome())
                .tamanho(dto.getTamanho())
                .cor(dto.getCor())
                .tipo(dto.getTipo())
                .categoria(dto.getCategoria())
                .subCategoria(dto.getSubCategoria())
                .preco(dto.getPreco())
                .descricao(dto.getDescricao())
                .urlImagem(dto.getUrlImagem())
                .build();
    }

}
