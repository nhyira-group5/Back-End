package API.nhyira.apivitalis.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflitoException extends RuntimeException {
    public ConflitoException(String entidade) {
        super(String.format("%S já esta em uso!", entidade));
    }
}
