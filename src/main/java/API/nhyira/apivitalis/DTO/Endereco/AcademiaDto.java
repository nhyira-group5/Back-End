package API.nhyira.apivitalis.DTO.Endereco;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AcademiaDto {
    private String nome;
    private String endereco;
    private double latitude;
    private double longitude;
    private double classificacao;
    private String horariosSemana;
    private String horariosFimDeSemana;
}
