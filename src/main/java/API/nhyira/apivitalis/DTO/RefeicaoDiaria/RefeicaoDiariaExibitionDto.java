package API.nhyira.apivitalis.DTO.RefeicaoDiaria;

import API.nhyira.apivitalis.DTO.RefeicaoPorDieta.RefeicaoPorDietaExibitionDto;
import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import API.nhyira.apivitalis.Entity.Refeicao;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class RefeicaoDiariaExibitionDto {



    private Integer idRefeicaoDiaria;


    private RefeicaoPorDietaExibitionDto.RefeicaoDto refeicaoId;

    private RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaId;

    private Integer concluido;

}
