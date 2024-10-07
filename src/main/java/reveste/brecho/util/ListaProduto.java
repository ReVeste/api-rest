package reveste.brecho.util;

import reveste.brecho.dto.produto.ProdutoPedidoDto;
import reveste.brecho.entity.itempedido.ItemPedido;
import reveste.brecho.entity.produto.Produto;

public class ListaProduto {

    private ProdutoPedidoDto[] vetor;
    private int nroElem;

    public ListaProduto(int tamanhoLista) {
        this.vetor = new ProdutoPedidoDto[tamanhoLista];
        this.nroElem = 0;
    }

    public void adiciona(ProdutoPedidoDto elementoProduto){
        if (nroElem >= vetor.length){
            throw new IllegalStateException();
        } else {
            vetor[nroElem] = elementoProduto;
            nroElem++;
        }
    }

    public int busca(ProdutoPedidoDto elementoPesquisa){
        for (int i = 0; i < nroElem; i++) {
            if (vetor[i] == elementoPesquisa){
                return i;
            }
        }
        return -1;
    }

    public boolean removePeloIndice(int indice){
        if (indice < 0 || indice >= nroElem){
            return false;
        }
        for (int i = indice; i < nroElem - 1; i++) {
            vetor[i] = vetor[i+1];
        }
        nroElem--;
        return true;
    }

    public boolean removeElemento(ProdutoPedidoDto elemento){
        if (busca(elemento) == -1){
            return false;
        }
        removePeloIndice(busca(elemento));
        return true;
    }

    public void exibir(){
        for (int i = 0; i < nroElem; i++) {
            System.out.println(vetor[i]);
        }
    }

    public ProdutoPedidoDto exibirPorIndex(int index){
        ProdutoPedidoDto produtoPedidoDto = new ProdutoPedidoDto();
        if (index < 0 || index >= nroElem){
            return null;
        }
        for (int i = index; i < nroElem - 1; i++) {
            if (i == index){
                produtoPedidoDto = vetor[i];
            }
        }
        return produtoPedidoDto;
    }

    public int size(){
        int tamanho = 0;
        for (int i = 0; i < nroElem; i++) {
            tamanho++;
        }
        return tamanho;
    }

    public ProdutoPedidoDto[] getVetor() {
        return vetor;
    }

    public int getNroElem() {
        return nroElem;
    }
}