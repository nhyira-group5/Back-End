package API.nhyira.apivitalis.DTO.Exercicio;

import API.nhyira.apivitalis.Entity.Exercicio;
import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.TagExercicio;
import API.nhyira.apivitalis.Service.MidiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ExercicioMapper {


    public ExercicioExibitionDto toDTO(Exercicio exercicio) {
        ExercicioExibitionDto dto = new ExercicioExibitionDto();
        dto.setIdExercicio(exercicio.getIdExercicio());
        dto.setNome(exercicio.getNome());
        dto.setDescricao(exercicio.getDescricao());
        dto.setMidiaList(exercicio.getMidia());
        return dto;
    }

    public static ExercicioExibition toDto(Exercicio exercicio) {
        ExercicioExibition dto = new ExercicioExibition();
        dto.setIdExercicio(exercicio.getIdExercicio());
        dto.setNome(exercicio.getNome());
        dto.setDescricao(exercicio.getDescricao());
        dto.setTagExercicioDtos(tagExibitionDto(exercicio.getTagExercicios()));
        dto.setIdMidia(midiaExibitionDtos(exercicio.getMidia()));
        return dto;
    }

    public static List<ExercicioExibition> toDto(List<Exercicio> exercicios){
        return exercicios.stream().map(ExercicioMapper::toDto).toList();
    }

    public static List<ExercicioExibition.TagExibitionDto> tagExibitionDto(List<TagExercicio> tagExercicioList){
        return tagExercicioList.stream().map(te -> {
            ExercicioExibition.TagExibitionDto tagExibitionDto = new ExercicioExibition.TagExibitionDto();

            tagExibitionDto.setIdTagExercicio(te.getIdTagExercicio());
            tagExibitionDto.setTagId(te.getTag());
            return tagExibitionDto;
        }).toList();
    }

    public static List<ExercicioExibition.MidiaExibitionDto> midiaExibitionDtos(List<Midia> midiaList){
        return midiaList.stream().map(mid ->{
            ExercicioExibition.MidiaExibitionDto midiaExibitionDto = new ExercicioExibition.MidiaExibitionDto();
            midiaExibitionDto.setCaminho(mid.getCaminho());
            midiaExibitionDto.setTipo(mid.getTipo());
            midiaExibitionDto.setExtensao(mid.getExtensao());
            midiaExibitionDto.setNome(mid.getNome());
            return midiaExibitionDto;
        }).toList();
    }

}