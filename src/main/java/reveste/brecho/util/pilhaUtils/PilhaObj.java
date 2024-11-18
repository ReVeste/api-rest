package reveste.brecho.util.pilhaUtils;

public class PilhaObj <T> {

    private T[] pilha;
    private int topo;

    public PilhaObj(int tamanho) {
        this.pilha = (T[]) new Object[tamanho];
        this.topo = -1;
    }

    public boolean isEmpty() {
        return topo == -1;
    }

    public boolean isFull() {
        return topo == pilha.length-1;
    }

    public void push (T info) {
        if (!isFull()) {
            pilha[++topo] = info;
        } else {
            throw new IllegalStateException("Pilha cheia!");
        }
    }

    public T pop() {
        if (!isEmpty()) {
            return pilha[topo--];
        } else {
            return null;
        }
    }

    public T peek() {
        if (!isEmpty()) {
            return pilha[topo];
        } else {
            return null;
        }
    }

    public void exibe() {
        if (isEmpty()) {
            System.out.println("Pilha vazia");
        } else {
            System.out.printf("\n%-10S|%-20S|", "Posição", "Valor");
            for (int i = topo; i > -1; i--) {
                System.out.printf("\n%10S|%20S|",
                        i, pilha[i]);
            }
            System.out.println("");
        }
    }

}
