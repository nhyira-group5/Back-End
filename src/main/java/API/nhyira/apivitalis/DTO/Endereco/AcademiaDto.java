package API.nhyira.apivitalis.DTO.Endereco;

import java.util.List;

public class AcademiaDto {
    private String nome;
    private String endereco;
    private double latitude;
    private double longitude;
    private double classificacao;
    private List<String> diasAbertos;

    public AcademiaDto() {
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(double classificacao) {
        this.classificacao = classificacao;
    }

    public List<String> getDiasAbertos() {
        return diasAbertos;
    }

    public void setDiasAbertos(List<String> diasAbertos) {
        this.diasAbertos = diasAbertos;
    }
}
