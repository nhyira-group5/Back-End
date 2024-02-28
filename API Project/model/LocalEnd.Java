import org.springframework.http.ResponseEntity;

public class LocalEnd {

    private String id;
    private String nome;
    private String endereco;
    private Object metricas;
    private Object localizacao;
    private Object feedbacks;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Object getMetricas() {
        return metricas;
    }

    public void setMetricas(Object metricas) {
        this.metricas = metricas;
    }

    public Object getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Object localizacao) {
        this.localizacao = localizacao;
    }

    public Object getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(Object feedbacks) {
        this.feedbacks = feedbacks;
    }

    
    public static LocalModel fromResponse(String id, String nome, String endereco, ResponseEntity<Object> metricasResponse, ResponseEntity<Object> localizacaoResponse, ResponseEntity<Object> feedbacksResponse) {
        LocalModel localModel = new LocalModel();
        localModel.setId(id);
        localModel.setNome(nome);
        localModel.setEndereco(endereco);
        localModel.setMetricas(metricasResponse.getBody());
        localModel.setLocalizacao(localizacaoResponse.getBody());
        localModel.setFeedbacks(feedbacksResponse.getBody());
        return localModel;
    }
}
