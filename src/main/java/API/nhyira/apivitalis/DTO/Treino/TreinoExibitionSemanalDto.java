package API.nhyira.apivitalis.DTO.Treino;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class TreinoExibitionSemanalDto {
    private Integer idTreino;
    private int idExercicio;
    private String nome;
    private String descricao;
    private LocalTime tempo;
    private Integer serie;
    private Integer repeticao;
    private Integer concluido;
    private RotinaDiariaDto rotinaDiaria;
    private List<MidiaDto> midia;

    @Data
    public static class MidiaDto {
        private Integer id;
        private String nome;
        private String caminho;
        private String extensao;
    }

    @Data
    public static class RotinaDiariaDto {
        private Integer id;
        private Integer dia;
    }
}
