import org.springframework.http.ResponseEntity;

public class ValidacaoContexto<T> {
    private ValidacaoStrategy<T> strategy;

    public ValidacaoContexto(ValidacaoStrategy<T> strategy) {
        this.strategy = strategy;
    }

    public ResponseEntity<String> executarValidacao(T objeto) {
        return strategy.validar(objeto);
    }
}
