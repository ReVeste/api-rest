package reveste.brecho.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class ConflitoException extends RuntimeException {

    public ConflitoException() {
        super();
    }

    public ConflitoException(String entidade) {
        super(String.format("%s com conflito.", entidade));
    }
}
