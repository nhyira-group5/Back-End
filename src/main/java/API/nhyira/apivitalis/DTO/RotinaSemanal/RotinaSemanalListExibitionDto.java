package API.nhyira.apivitalis.DTO.RotinaSemanal;

import lombok.Data;

import java.util.List;

@Data
public class RotinaSemanalListExibitionDto {
    private Integer id;
    private RotinaSemanalExibitionDto.RotinaMensalExibitionDto rotinaMensalId;
    private Integer numSemana;
    private Integer concluido;
}
