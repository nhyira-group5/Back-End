import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.MetricsEndpoint;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ServicoAPIBackend {

    private static final String URL_API_GOOGLE_PLACES = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private MetricsEndpoint metricsEndpoint;
    
    
    public ResponseEntity<String> buscarLocaisProximos(double latitude, double longitude, String palavraChave, String chaveAPI) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_GOOGLE_PLACES)
                .queryParam("location", String.format("%f,%f", latitude, longitude))
                .queryParam("radius", 5000)
                .queryParam("keyword", palavraChave)
                .queryParam("key", chaveAPI);

        return restTemplate.getForEntity(builder.toUriString(), String.class);
    }
    
    
    public ResponseEntity<Object> obterMetricasDoLocal(String localId) {
        Object metrics = metricsEndpoint.metric(localId, null);
        return ResponseEntity.ok(metrics);
    }
   
 
    public ResponseEntity<Object> obterLocalizacaoDoLocal(String localId) {
       
        Object localizacao = null;al
        return ResponseEntity.ok(localizacao);
    }
    
    
    public ResponseEntity<Object> obterFeedbacksDoLocal(String localId) {
        
        Object feedbacks = null; 
        return ResponseEntity.ok(feedbacks);
    }
}
