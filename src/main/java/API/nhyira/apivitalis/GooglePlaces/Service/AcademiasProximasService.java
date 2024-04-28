package API.nhyira.apivitalis.GooglePlaces.Service;

import API.nhyira.apivitalis.Entity.EnderecoModel;
import API.nhyira.apivitalis.Repository.EnderecoRepository;
import com.google.maps.errors.ApiException;
import com.google.maps.model.*;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.PlacesApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class AcademiasProximasService {

    private final String API_KEY = "AIzaSyBoSledzM_6LdnLqTwEFxCVbskfjqtZz2c";

    @Autowired
    private EnderecoRepository enderecoRepository;

    public AcademiasProximasService() {
    }

    public String buscarAcademiasProximas(String logradouro, String bairro, String cidade, String estado, String cep) {
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

            List<Double> classificacoes = new ArrayList<>();
            List<PlaceDetails> academias = new ArrayList<>();
            double totalClassificacao = 0;
            double totalPreco = 0;

            StringBuilder resultado = new StringBuilder();
            for (PlacesSearchResult lugar : response.results) {
                PlaceDetails detalhesLugar = PlacesApi.placeDetails(context, lugar.placeId).await();
                double classificacao = (detalhesLugar.rating != 0) ? detalhesLugar.rating : -1;
                classificacoes.add(classificacao);
                academias.add(detalhesLugar); 
                totalClassificacao += classificacao;

                if (detalhesLugar.priceLevel != null) {
                    totalPreco += detalhesLugar.priceLevel.ordinal() + 1;
                }

                EnderecoModel endereco = new EnderecoModel();
                endereco.setLogradouro(logradouro);
                endereco.setBairro(bairro);
                endereco.setCidade(cidade);
                endereco.setEstado(estado);
                endereco.setCep(cep);
                enderecoRepository.save(endereco);

                resultado.append("```");
                resultado.append("\uD83D\uDD25 Nome: **").append(lugar.name).append("**\n");
                resultado.append("\uD83C\uDFE0 Endereço: *").append(lugar.vicinity).append("*\n");
                resultado.append("\uD83C\uDF10 Latitude: *").append(lugar.geometry.location.lat).append("*\n");
                resultado.append("\uD83C\uDF11 Longitude: *").append(lugar.geometry.location.lng).append("*\n");
                resultado.append("\uD83C\uDF1F Classificação: *").append(classificacao).append("*\n");

                if (detalhesLugar.priceLevel != null) {
                    resultado.append("💰 Nível de Preço: ");
                    for (int i = 0; i < detalhesLugar.priceLevel.ordinal() + 1; i++) {
                        resultado.append("$");
                    }
                    resultado.append("\n");
                }

                if (detalhesLugar.openingHours != null) {
                    resultado.append("⏰ Horário de Funcionamento: \n");
                    for (String horarioDia : detalhesLugar.openingHours.weekdayText) {
                        resultado.append(horarioDia.replace("Monday", "Segunda-feira")
                                .replace("Tuesday", "Terça-feira")
                                .replace("Wednesday", "Quarta-feira")
                                .replace("Thursday", "Quinta-feira")
                                .replace("Friday", "Sexta-feira")
                                .replace("Saturday", "Sábado")
                                .replace("Sunday", "Domingo")).append("\n");
                    }
                }
                resultado.append("```");
                resultado.append("\n");
            }




            int posicaoAcademia = pesquisaBinaria(academias, 0, academias.size() - 1, 4.5); 

            quickSort(classificacoes, 0, classificacoes.size() - 1);

            double mediaClassificacao = classificacoes.isEmpty() ? 0 : totalClassificacao / classificacoes.size();
            double mediaPreco = response.results.length == 0 ? 0 : totalPreco / response.results.length;

            resultado.append("```\n");
            resultado.append("Total de Academias Encontradas: ").append(response.results.length).append(" \uD83C\uDFEB\n");
            resultado.append("Classificação Média das Academias: ").append(String.format("%.2f", mediaClassificacao)).append(" \uD83C\uDF1F\n");
            resultado.append("Preço Médio: ").append(LevelSimbolo(mediaPreco)).append(" \uD83D\uDCB0\n");
            resultado.append("```");

            return resultado.toString();
        } catch (ApiException | InterruptedException | IOException e) {
            e.printStackTrace();
            return "⚠️ Ocorreu um erro ao buscar academias próximas. Por favor, tente novamente mais tarde.";
        }
    }


    private void quickSort(List<Double> arr, int baixo, int alto) {
        if (baixo < alto) {
            int pi = particionar(arr, baixo, alto);
            quickSort(arr, baixo, pi - 1);
            quickSort(arr, pi + 1, alto);
        }
    }

    private int particionar(List<Double> arr, int baixo, int alto) {
        double pivo = arr.get(alto);
        int i = baixo - 1;
        for (int j = baixo; j < alto; j++) {
            if (arr.get(j) < pivo) {
                i++;
                trocar(arr, i, j);
            }
        }
        trocar(arr, i + 1, alto);
        return i + 1;
    }

    private void trocar(List<Double> arr, int i, int j) {
        double temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }

    private String LevelSimbolo(double mediaPreco) {
        return (mediaPreco < 1.5) ? "$" : "$$";
    }

    public int calcularFatorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return n * calcularFatorial(n - 1);
        }
    }

   
    private int pesquisaBinaria(List<PlaceDetails> academias, int inicio, int fim, double nota) {
        if (fim >= inicio) {
            int meio = inicio + (fim - inicio) / 2;


            return pesquisaBinaria(academias, inicio, meio - 1, nota);
        }

    
        return -1;
    }
}
