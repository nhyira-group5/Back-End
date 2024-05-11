package API.nhyira.apivitalis.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class SemConteudoException extends RuntimeException{

    public SemConteudoException(String entidade) {
        super(String.format("%S sem conteúdo", entidade));
    }
}
