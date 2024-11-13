package reveste.brecho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AcessoProibidoException extends RuntimeException {

    public AcessoProibidoException(String entidade) {
        super(String.format("Acesso proibido para %s", entidade));
    }
}
