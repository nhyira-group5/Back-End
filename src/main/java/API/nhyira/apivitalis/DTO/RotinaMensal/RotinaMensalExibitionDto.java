package API.nhyira.apivitalis.DTO.RotinaMensal;

import API.nhyira.apivitalis.Entity.Meta;
import lombok.Data;

import java.util.List;

@Data
public class RotinaMensalExibitionDto {
    private int id;
    private RotinaUsuarioDto rotinaUsuarioId;
    private int mes;
    private int ano;
    private int concluido;
    private List<RotinaSemanalDto> rotinaSemanalDtos;

    @Data
    public static class RotinaUsuarioDto{
        private int id;
        private Meta metaId;
    }

    @Data
    public static class RotinaSemanalDto{
        private int id;
        private Integer concluido;
    }
}
