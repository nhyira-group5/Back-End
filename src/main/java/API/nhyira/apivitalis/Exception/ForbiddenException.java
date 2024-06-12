package API.nhyira.apivitalis.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String entidade) {
        super(String.format("%S não pode realizar essa ação", entidade));
    }
}