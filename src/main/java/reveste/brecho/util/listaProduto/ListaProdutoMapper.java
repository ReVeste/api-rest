package reveste.brecho.util.listaProduto;

import reveste.brecho.dto.produto.ProdutoDTO;

import java.util.List;

public class ListaProdutoMapper {

    public static ListaProduto toListaProduto(List<ProdutoDTO> produtos){

        if (produtos == null) return null;

        ListaProduto produtosListados = new ListaProduto(produtos.size());

        for (int i = 0; i < produtosListados.size(); i++) {
            produtosListados.adiciona(produtos.get(i));
        }

        return produtosListados;

    }

}
