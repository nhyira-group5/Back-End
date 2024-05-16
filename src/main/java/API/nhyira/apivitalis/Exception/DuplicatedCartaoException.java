package API.nhyira.apivitalis.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicatedCartaoException extends RuntimeException {
    public DuplicatedCartaoException(String entidade) {
        super(String.format("Já existe um cartão com %S", entidade));
    }
}
