package API.nhyira.apivitalis.DTO.Treino;

import API.nhyira.apivitalis.DTO.Exercicio.ExercicioExibition;
import API.nhyira.apivitalis.DTO.Midia.MidiaExibitionDto;
import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoExibitionSemanalDto;
import API.nhyira.apivitalis.DTO.RotinaSemanal.RotinaSemanalExibitionDto;
import API.nhyira.apivitalis.Entity.*;

import java.util.ArrayList;
import java.util.List;

public class TreinoMapper {

    public static TreinoExibitionDto toDto(Treino treino){
        if (treino == null)return null;

        TreinoExibitionDto treinoExibitionDto = new TreinoExibitionDto();
        treinoExibitionDto.setIdTreino(treino.getIdTreino());
        treinoExibitionDto.setSerie(treino.getSerie());
        treinoExibitionDto.setTempo(treino.getTempo());
        treinoExibitionDto.setConcluido(treino.getConcluido());
        treinoExibitionDto.setRepeticao(treino.getIdTreino());
        treinoExibitionDto.setRotinaDiariaId(rotinaDiariaDto(treino.getRotinaDiariaId()));
        treinoExibitionDto.setExercicioId(exercicioDto(treino.getExercicioId()));
        return treinoExibitionDto;
    }

    public static List<TreinoExibitionDto> toDto(List<Treino> treinos){
        return treinos.stream().map(TreinoMapper::toDto).toList();
    }

    public static ExercicioExibition exercicioDto(Exercicio exercicio){
        if(exercicio == null)return null;

        ExercicioExibition exercicioDto = new ExercicioExibition();
        exercicioDto.setIdExercicio(exercicio.getIdExercicio());
        exercicioDto.setNome(exercicio.getNome());
        exercicioDto.setDescricao(exercicio.getDescricao());
        exercicioDto.setTagExercicioDtos(tagExibitionDto(exercicio.getTagExercicios()));
        exercicioDto.setIdMidia(midiaExibitionDto(exercicio.getMidia()));
        return exercicioDto;
    }

    public static List<ExercicioExibition.MidiaExibitionDto> midiaExibitionDto(List<Midia> midiaExibitionDtos){
        return midiaExibitionDtos.stream().map(md -> {
            ExercicioExibition.MidiaExibitionDto midiaExibitionDto = new ExercicioExibition.MidiaExibitionDto();
            midiaExibitionDto.setIdMidia(md.getIdMidia());
            midiaExibitionDto.setNome(md.getNome());
            midiaExibitionDto.setTipo(md.getTipo());
            midiaExibitionDto.setCaminho(md.getCaminho());
            midiaExibitionDto.setExtensao(md.getExtensao());
            return midiaExibitionDto;
        }).toList();
    }

    public static List<ExercicioExibition.TagExibitionDto> tagExibitionDto(List<TagExercicio> tagExercicioList){
        return tagExercicioList.stream().map(te -> {
            ExercicioExibition.TagExibitionDto tagExibitionDto = new ExercicioExibition.TagExibitionDto();

            tagExibitionDto.setIdTagExercicio(te.getIdTagExercicio());
            tagExibitionDto.setTagId(te.getTag());
            return tagExibitionDto;
        }).toList();
    }

    public static RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaDto(RotinaDiaria rotinaDiaria){
        if (rotinaDiaria == null)return null;
        RotinaSemanalExibitionDto.RotinaDiariaDto rotinaDiariaDto = new RotinaSemanalExibitionDto.RotinaDiariaDto();
        rotinaDiariaDto.setIdRotinaDiaria(rotinaDiaria.getIdRotinaDiaria());
        rotinaDiariaDto.setConcluido(rotinaDiaria.getConcluido());
        return rotinaDiariaDto;
    }

    public static List<TreinoRelatorioDto> toDtoRelatorio(List<Integer> count, List<String> nome){
        List<TreinoRelatorioDto> relatorioDtos = new ArrayList<>();
        int size = Math.min(count.size(), nome.size());
        for (int i = 0; i < size; i++) {
            TreinoRelatorioDto dto = new TreinoRelatorioDto();
            dto.setConcluido(count.get(i));
            dto.setNome(nome.get(i));
            relatorioDtos.add(dto);
        }

        return relatorioDtos;
    }



    // Controller /treinos/por-semana/{idRotinaSemanal}
    // Controller /treinos/por-dia/{idRotinaDiaria}
    public static TreinoExibitionSemanalDto toTreinoExibitionSemanalDto (Exercicio ex, Treino treino, RotinaDiaria rd) {
        if (ex == null || treino == null || rd == null) return null;

        TreinoExibitionSemanalDto treinoSemanalDto = new TreinoExibitionSemanalDto();
        treinoSemanalDto.setIdTreino(treino.getIdTreino());
        treinoSemanalDto.setIdExercicio(ex.getIdExercicio());
        treinoSemanalDto.setNome(ex.getNome());
        treinoSemanalDto.setDescricao(ex.getDescricao());
        treinoSemanalDto.setTempo(treino.getTempo());
        treinoSemanalDto.setSerie(treino.getSerie());
        treinoSemanalDto.setRepeticao(treino.getRepeticao());
        treinoSemanalDto.setConcluido(treino.getConcluido());
        treinoSemanalDto.setRotinaDiaria(toRotinaDiaria(rd));
        treinoSemanalDto.setMidia(toSemanalMidiaDto(ex.getMidia()));
        return treinoSemanalDto;
    }

    public static TreinoExibitionSemanalDto.RotinaDiariaDto toRotinaDiaria (RotinaDiaria refeicaoDiaria) {
        if (refeicaoDiaria == null) return null;

        TreinoExibitionSemanalDto.RotinaDiariaDto rd = new TreinoExibitionSemanalDto.RotinaDiariaDto();
        rd.setId(refeicaoDiaria.getIdRotinaDiaria());
        rd.setDia(refeicaoDiaria.getDia());
        return rd;
    }

    public static List<TreinoExibitionSemanalDto.MidiaDto> toSemanalMidiaDto (List<Midia> midia) {
        return midia.stream().map(mi ->{
            TreinoExibitionSemanalDto.MidiaDto midiaDto = new TreinoExibitionSemanalDto.MidiaDto();
            midiaDto.setId(mi.getIdMidia());
            midiaDto.setNome(mi.getNome());
            midiaDto.setCaminho(mi.getCaminho());
            midiaDto.setExtensao(mi.getExtensao());
            return midiaDto;
        }).toList();
    }
}
