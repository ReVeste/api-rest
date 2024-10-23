package reveste.brecho.util;

import reveste.brecho.entity.itempedido.ItemPedido;

import java.util.List;

public class PesquisaBinaria {

    public static int buscarItemProdutoPorProduto(List<ItemPedido> listaProduto, int idProdutoPesquisa){

        int indinf = 0;
        int indsup = listaProduto.size() - 1;
        int meio;
        while (indinf <= indsup){
            meio = (indinf + indsup) / 2;
            if (listaProduto.get(meio).getProduto().getId() == idProdutoPesquisa){
                return meio;
            } else if (idProdutoPesquisa < listaProduto.get(meio).getProduto().getId()) {
                indsup = meio - 1;
            } else {
                indinf = meio + 1;
            }
        }
        return -1;
    }

}
