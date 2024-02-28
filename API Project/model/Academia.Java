import org.springframework.http.ResponseEntity;

public class AcademiaModel {

    private ResponseEntity<String> nome;
    private ResponseEntity<String> endereco;
    private ResponseEntity<Double> indiceRecomendacao;

    
    public AcademiaModel(ResponseEntity<String> nome, ResponseEntity<String> endereco, ResponseEntity<Double> indiceRecomendacao) {
        this.nome = nome;
        this.endereco = endereco;
        this.indiceRecomendacao = indiceRecomendacao;
    }

    /
    public ResponseEntity<String> getNome() {
        return nome;
    }

    public void setNome(ResponseEntity<String> nome) {
        this.nome = nome;
    }

    public ResponseEntity<String> getEndereco() {
        return endereco;
    }

    public void setEndereco(ResponseEntity<String> endereco) {
        this.endereco = endereco;
    }

    public ResponseEntity<Double> getIndiceRecomendacao() {
        return indiceRecomendacao;
    }

    public void setIndiceRecomendacao(ResponseEntity<Double> indiceRecomendacao) {
        this.indiceRecomendacao = indiceRecomendacao;
    }
}
