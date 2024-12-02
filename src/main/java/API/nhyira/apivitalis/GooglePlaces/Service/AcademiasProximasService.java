package API.nhyira.apivitalis.GooglePlaces.Service;

import API.nhyira.apivitalis.DTO.Endereco.AcademiaDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;

import java.io.IOException;
import java.util.*;

@Service
public class AcademiasProximasService {

    private final String API_KEY = "AIzaSyDjShZAnlDFZiEw-AYGIj12Gohui3N7fAw";

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
                if (academiasDto.size() >= 7) break;

                PlaceDetails detalhesLugar = PlacesApi.placeDetails(context, lugar.placeId).await();

                if (detalhesLugar.openingHours == null || detalhesLugar.name == null || detalhesLugar.vicinity == null || detalhesLugar.geometry == null) {
                    continue;
                }

                List<String> horariosTraduzidos = traduzirDiasParaPortugues(Arrays.asList(detalhesLugar.openingHours.weekdayText));
                Map<String, String> horariosFormatados = formatarHorarios(horariosTraduzidos);


                if (horariosFormatados.get("semana").isEmpty()) {
                    continue;
                }

                double classificacao = (detalhesLugar.rating != 0) ? detalhesLugar.rating : -1;

                AcademiaDto academiaDto = new AcademiaDto();
                academiaDto.setNome(lugar.name);
                academiaDto.setEndereco(lugar.vicinity);
                academiaDto.setLatitude(lugar.geometry.location.lat);
                academiaDto.setLongitude(lugar.geometry.location.lng);
                academiaDto.setClassificacao(classificacao);
                academiaDto.setHorariosSemana(horariosFormatados.get("semana"));
                academiaDto.setHorariosFimDeSemana(horariosFormatados.get("fimDeSemana"));

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

    private Map<String, String> formatarHorarios(List<String> horarios) {
        StringBuilder semana = new StringBuilder();
        StringBuilder fimDeSemana = new StringBuilder();

        for (String horario : horarios) {
            if (horario.contains("Segunda-feira") || horario.contains("Terça-feira") || horario.contains("Quarta-feira") ||
                    horario.contains("Quinta-feira") || horario.contains("Sexta-feira")) {
                semana.append(horario).append(" ");
            } else if (horario.contains("Sábado") || horario.contains("Domingo")) {
                fimDeSemana.append(horario).append(" ");
            }
        }

        Map<String, String> horariosFormatados = new HashMap<>();
        horariosFormatados.put("semana", semana.toString().trim());
        horariosFormatados.put("fimDeSemana", fimDeSemana.toString().trim());

        return horariosFormatados;
    }

    private List<String> traduzirDiasParaPortugues(List<String> diasEmIngles) {
        Map<String, String> mapaTraducao = new HashMap<>();
        mapaTraducao.put("Monday", "Segunda-feira");
        mapaTraducao.put("Tuesday", "Terça-feira");
        mapaTraducao.put("Wednesday", "Quarta-feira");
        mapaTraducao.put("Thursday", "Quinta-feira");
        mapaTraducao.put("Friday", "Sexta-feira");
        mapaTraducao.put("Saturday", "Sábado");
        mapaTraducao.put("Sunday", "Domingo");

        List<String> diasEmPortugues = new ArrayList<>();
        for (String dia : diasEmIngles) {
            for (Map.Entry<String, String> entrada : mapaTraducao.entrySet()) {
                if (dia.contains(entrada.getKey())) {
                    diasEmPortugues.add(dia.replace(entrada.getKey(), entrada.getValue()));
                }
            }
        }
        return diasEmPortugues;
    }
}
