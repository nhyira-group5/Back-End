package API.nhyira.apivitalis.GooglePlaces.Service;

import API.nhyira.apivitalis.DTO.Endereco.AcademiaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcademiasProximasService {

    private final String API_KEY = "AIzaSyBoSledzM_6LdnLqTwEFxCVbskfjqtZz2c";

    public ResponseEntity<List<AcademiaDto>> buscarAcademiasProximas(String logradouro, String bairro, String cidade, String estado, String cep) {
        try {
            GeoApiContext context = new GeoApiContext.Builder()
                    .apiKey(API_KEY)
                    .build();

            String enderecoCompleto = logradouro + ", " + bairro + ", " + cidade + ", " + estado + ", " + cep;
            GeocodingResult[] results = GeocodingApi.geocode(context, enderecoCompleto).await();
            LatLng localUsuario = results[0].geometry.location;

            PlacesSearchResponse response = PlacesApi.nearbySearchQuery(context, localUsuario)
                    .type(PlaceType.GYM)
                    .rankby(RankBy.DISTANCE)
                    .await();

            List<AcademiaDto> academiasDto = new ArrayList<>();
            for (PlacesSearchResult lugar : response.results) {
                PlaceDetails detalhesLugar = PlacesApi.placeDetails(context, lugar.placeId).await();


                if (detalhesLugar.openingHours == null || detalhesLugar.name == null || detalhesLugar.vicinity == null || detalhesLugar.geometry == null) {
                    continue;
                }

                double classificacao = (detalhesLugar.rating != 0) ? detalhesLugar.rating : -1;

                AcademiaDto academiaDto = new AcademiaDto();
                academiaDto.setNome(lugar.name);
                academiaDto.setEndereco(lugar.vicinity);
                academiaDto.setLatitude(lugar.geometry.location.lat);
                academiaDto.setLongitude(lugar.geometry.location.lng);
                academiaDto.setClassificacao(classificacao);
                academiaDto.setDiasAbertos(List.of(detalhesLugar.openingHours.weekdayText));

                academiasDto.add(academiaDto);
            }

            mergeSort(academiasDto, 0, academiasDto.size() - 1);

            return ResponseEntity.status(HttpStatus.OK).body(academiasDto);
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    private void mergeSort(List<AcademiaDto> academias, int inicio, int fim) {
        if (inicio < fim) {
            int meio = inicio + (fim - inicio) / 2;

            mergeSort(academias, inicio, meio);
            mergeSort(academias, meio + 1, fim);

            merge(academias, inicio, meio, fim);
        }
    }

    private void merge(List<AcademiaDto> academias, int inicio, int meio, int fim) {
        int tamanhoEsquerda = meio - inicio + 1;
        int tamanhoDireita = fim - meio;

        List<AcademiaDto> listaEsquerda = new ArrayList<>(tamanhoEsquerda);
        List<AcademiaDto> listaDireita = new ArrayList<>(tamanhoDireita);

        for (int i = 0; i < tamanhoEsquerda; i++) {
            listaEsquerda.add(academias.get(inicio + i));
        }

        for (int j = 0; j < tamanhoDireita; j++) {
            listaDireita.add(academias.get(meio + 1 + j));
        }

        int i = 0, j = 0;
        int k = inicio;

        while (i < tamanhoEsquerda && j < tamanhoDireita) {
            if (listaEsquerda.get(i).getClassificacao() >= listaDireita.get(j).getClassificacao()) {
                academias.set(k, listaEsquerda.get(i));
                i++;
            } else {
                academias.set(k, listaDireita.get(j));
                j++;
            }
            k++;
        }

        while (i < tamanhoEsquerda) {
            academias.set(k, listaEsquerda.get(i));
            i++;
            k++;
        }

        while (j < tamanhoDireita) {
            academias.set(k, listaDireita.get(j));
            j++;
            k++;
        }
    }

    private int buscaBinariaPorNome(List<AcademiaDto> academias, String nomeAcademia) {
        int esquerda = 0;
        int direita = academias.size() - 1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            int comparacao = academias.get(meio).getNome().compareTo(nomeAcademia);

            if (comparacao == 0) {
                return meio;
            }

            if (comparacao < 0) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }

        return -1;
    }


    public List<AcademiaDto> buscarAcademiaPorNome(String nomeAcademia) {
        ResponseEntity<List<AcademiaDto>> responseEntity = buscarAcademiasProximas("logradouro", "bairro", "cidade", "estado", "cep");

        if (responseEntity != null && responseEntity.getBody() != null && !responseEntity.getBody().isEmpty()) {
            List<AcademiaDto> response = responseEntity.getBody();
            List<AcademiaDto> academiasEncontradas = new ArrayList<>();

            for (AcademiaDto academia : response) {
                if (academia.getNome().equalsIgnoreCase(nomeAcademia)) {
                    academiasEncontradas.add(academia);
                }
            }

            if (!academiasEncontradas.isEmpty()) {
                return academiasEncontradas;
            }
        }

        return null;
    }

}
