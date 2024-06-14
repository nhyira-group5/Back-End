package API.nhyira.apivitalis.DTO.RotinaSemanal;

import lombok.Data;

import java.util.List;

@Data
public class RotinaSemanalExibitionDto {
    private Integer id;
    private RotinaMensalExibitionDto rotinaMensalId;
    private Integer numSemana;
    private Integer concluido;
    private List<RotinaDiariaDto> rotinaDiariaDtos;

    @Data
    public static class RotinaMensalExibitionDto{
        private int id;
        private int mes;
        private int ano;
    }

    @Data
    public static class RotinaDiariaDto{
        private Integer idRotinaDiaria;
        private Integer concluido;
    }
}
