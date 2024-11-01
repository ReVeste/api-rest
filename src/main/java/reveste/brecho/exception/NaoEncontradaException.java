package reveste.brecho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class NaoEncontradaException extends RuntimeException{

    public NaoEncontradaException() {
        super();
    }

    public NaoEncontradaException(String entidade) {
        super(String.format("%s não encontrado(a)", entidade));
    }

    public NaoEncontradaException(String entidade, String campo) {
        super(String.format("%s com o campo %s não encontrado(a)", entidade, campo));
    }

    public NaoEncontradaException(String entidade, Throwable cause) {
        super(String.format("%s não encontrado(a)", entidade), cause);
    }

    public NaoEncontradaException(String entidade, Integer id) {
        super(String.format("%s não encontrado(a) para o Id: %d", entidade, id));
    }
}
