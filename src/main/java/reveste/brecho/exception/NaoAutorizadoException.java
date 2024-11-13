package reveste.brecho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class NaoAutorizadoException extends RuntimeException {

    public NaoAutorizadoException(String entidade) {
        super(String.format("Acesso não autorizado para %s", entidade));
    }
}
