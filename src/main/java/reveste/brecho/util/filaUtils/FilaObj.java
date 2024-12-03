package reveste.brecho.util.filaUtils;

import java.util.Arrays;

public class FilaObj <T> {

    private int tamanho;
    private T[] fila;

    public FilaObj(int capacidade) {
        this.tamanho = 0;
        this.fila = (T[]) new Object[capacidade];
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public boolean isFull() {
        return tamanho == fila.length;
    }

    public void insert(T info) {
        if (isFull()){
            throw new IllegalStateException("Fila cheia");
        }
        fila[tamanho++] = info;
    }

    public T peek() {
        return fila[0];
    }

    public T poll() {
        if (!isEmpty()) {
            T aux = fila[0];
            for (int i = 0; i < tamanho-1; i++) {
                fila[i] = fila[i+1];
            }
            tamanho--;
            return aux;
        }
        return null;
    }

    public Integer[] exibe() {
        if (isEmpty()) {
            System.out.println("Fila vazia");
            return new Integer[0]; // Retorna um array vazio ao invés de null.
        } else {
            System.out.printf("\n%-10S|%-20S|", "Posição", "Valor");
            for (int i = 0; i < tamanho; i++) {
                System.out.printf("\n%10S|%20S|", i, fila[i]);
            }
            System.out.println("");
            return Arrays.copyOf(fila, tamanho, Integer[].class); // Converte para Integer[].
        }
    }


    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public T[] getFila() {
        return fila;
    }

    public void setFila(T[] fila) {
        this.fila = fila;
    }

}
