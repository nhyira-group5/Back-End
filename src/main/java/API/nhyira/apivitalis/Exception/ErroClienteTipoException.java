package API.nhyira.apivitalis.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ErroClienteTipoException extends RuntimeException{
    public ErroClienteTipoException(String entidade) {
        super(String.format("%S tipo esta errado", entidade));
    }
}
