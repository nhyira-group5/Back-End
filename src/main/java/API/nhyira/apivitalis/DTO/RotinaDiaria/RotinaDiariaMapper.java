package API.nhyira.apivitalis.DTO.RotinaDiaria;

import API.nhyira.apivitalis.DTO.RotinaMensal.RotinaMensalExibitionDto;
import API.nhyira.apivitalis.Entity.RefeicaoDiaria;
import API.nhyira.apivitalis.Entity.RotinaDiaria;
import API.nhyira.apivitalis.Entity.RotinaSemanal;
import API.nhyira.apivitalis.Entity.Treino;
import org.apache.maven.lifecycle.internal.LifecycleStarter;

import java.util.List;

public class RotinaDiariaMapper {

    public static RotinaDiariaExibitionDto toDto(RotinaDiaria rotinaDiaria){
        RotinaDiariaExibitionDto rotinaDiariaExibitionDto = new RotinaDiariaExibitionDto();

        rotinaDiariaExibitionDto.setIdRotinaDiaria(rotinaDiaria.getIdRotinaDiaria());
        rotinaDiariaExibitionDto.setConcluido(rotinaDiaria.getConcluido());
        rotinaDiariaExibitionDto.setRotinaSemanalId(rotinaSemanalDto(rotinaDiaria.getRotinaSemanalId()));
        rotinaDiariaExibitionDto.setTreinoDto(treinoDtos(rotinaDiaria.getTreinoId()));
        rotinaDiariaExibitionDto.setRefeicaoDiariaDtos(refeicaoDiariaDtos(rotinaDiaria.getRefeicaoDiariaId()));
        rotinaDiariaExibitionDto.setDia(rotinaDiaria.getDia());
        return rotinaDiariaExibitionDto;
    }

    public static List<RotinaDiariaExibitionDto> toDtos(List<RotinaDiaria> rotinaDiarias) {
        return rotinaDiarias.stream().map(RotinaDiariaMapper::toDto).toList();
    }


    public static RotinaMensalExibitionDto.RotinaSemanalDto rotinaSemanalDto(RotinaSemanal rotinaSemanal){
        if (rotinaSemanal == null)return null;
        RotinaMensalExibitionDto.RotinaSemanalDto rotinaSemanalDto = new RotinaMensalExibitionDto.RotinaSemanalDto();
        rotinaSemanalDto.setId(rotinaSemanal.getIdRotinaSemanal());
        rotinaSemanalDto.setConcluido(rotinaSemanal.getConcluido());
        return rotinaSemanalDto;
    }

    public static List<RotinaDiariaExibitionDto.TreinoDto> treinoDtos(List<Treino> treinos){
        return treinos.stream().map(t ->{
            RotinaDiariaExibitionDto.TreinoDto treinoDto = new RotinaDiariaExibitionDto.TreinoDto();
            treinoDto.setIdTreino(t.getIdTreino());
            treinoDto.setConcluido(t.getRepeticao());
            treinoDto.setSerie(t.getSerie());
            treinoDto.setRepeticao(t.getRepeticao());
            treinoDto.setTempo(t.getTempo());
            return treinoDto;
        }).toList();
    }


    public static List<RotinaDiariaExibitionDto.RefeicaoDiariaDto> refeicaoDiariaDtos(List<RefeicaoDiaria> refeicaoDiarias){
        return refeicaoDiarias.stream().map(rd -> {
            RotinaDiariaExibitionDto.RefeicaoDiariaDto refeicaoDiariaDto = new RotinaDiariaExibitionDto.RefeicaoDiariaDto();
            refeicaoDiariaDto.setConcluido(rd.getConcluido());
            refeicaoDiariaDto.setIdRotinaDiaria(rd.getIdRefeicaoDiaria());
            return refeicaoDiariaDto;
        }).toList();

    }
}
