import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ServicoAPIExterna {

    private static final String URL_API_GOOGLE_PLACES = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";
    
    private final RestTemplate restTemplate;
    private final String googleApiKey;

    public ServicoAPIExterna(RestTemplate restTemplate, @Value("${google.api.key}") String googleApiKey) {
        this.restTemplate = restTemplate;
        this.googleApiKey = googleApiKey;
    }

    public ResponseEntity<String> buscarAcademiasProximas(String tipoLocal) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_GOOGLE_PLACES)
                .queryParam("type", tipoLocal)
                .queryParam("key", googleApiKey);

        return restTemplate.getForEntity(builder.toUriString(), String.class);
    }
}
