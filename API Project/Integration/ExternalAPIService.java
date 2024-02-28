import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Value;

@Service
public class ExternalAPIService {

    @Value("${google.api.key}")
    private String googleApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> buscarLocaisProximos(double latitude, double longitude, String palavraChave) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        
        String url = "https://maps.googleapis.com/maps/api/place/nearbysearch/json" +
                     "?location=" + latitude + "," + longitude +
                     "&radius=5000" +
                     "&keyword=" + palavraChave +
                     "&key=" + googleApiKey;

        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    public ResponseEntity<Object> obterMetricas() {
      
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }
}
