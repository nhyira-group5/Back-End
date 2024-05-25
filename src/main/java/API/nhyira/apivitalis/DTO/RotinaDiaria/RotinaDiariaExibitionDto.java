package API.nhyira.apivitalis.DTO.RotinaDiario;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.Entity.Midia;
import lombok.Data;

import java.sql.Time;
import java.time.LocalDate;

@Data
public class RotinaDiarioExibitionDto {

    private Integer idTreinoDiario;

    private RotinaMensalExibitionDto.RotinaSemanalDto rotinaSemanalId;

    private Integer concluido;





}
