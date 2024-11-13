package reveste.brecho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) // Status HTTP 400 para requisições inválidas
public class ArgumentoInvalidoException extends RuntimeException {

    public ArgumentoInvalidoException() {
        super();
    }

    public ArgumentoInvalidoException(String entidade, String detalhe) {
        super(String.format("Argumento inválido para %s: O %s não pode ser nulo", entidade, detalhe));
    }

    public ArgumentoInvalidoException(String detalhe) {
        super(String.format("O %s não pode ser nulo", detalhe));
    }
}