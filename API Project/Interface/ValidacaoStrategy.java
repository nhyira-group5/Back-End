import org.springframework.http.ResponseEntity;

public interface ValidacaoStrategy<T> {
    ResponseEntity<String> validar(T objeto);
}
