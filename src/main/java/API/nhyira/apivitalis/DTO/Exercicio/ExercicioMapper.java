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
    @Autowired
    private MidiaService midiaService;

    public ExercicioExibitionDto toDTO(Exercicio exercicio) {
        ExercicioExibitionDto dto = new ExercicioExibitionDto();
        dto.setIdExercicio(exercicio.getIdExercicio());
        dto.setNome(exercicio.getNome());
        dto.setDescricao(exercicio.getDescricao());

        Midia midia = exercicio.getMidia();
        dto.setMidiaNome(midia.getNome());
        dto.setMidiaCaminho(midia.getCaminho());
        dto.setMidiaExtensao(midia.getExtensao());
        return dto;
    }

    public static ExercicioExibition toDto(Exercicio exercicio) {
        ExercicioExibition dto = new ExercicioExibition();
        dto.setIdExercicio(exercicio.getIdExercicio());
        dto.setNome(exercicio.getNome());
        dto.setDescricao(exercicio.getDescricao());
        dto.setTagExercicioDtos(tagExibitionDto(exercicio.getTagExercicios()));

        Midia midia = exercicio.getMidia();
        dto.setMidiaNome(midia.getNome());
        dto.setMidiaCaminho(midia.getCaminho());
        dto.setMidiaExtensao(midia.getExtensao());
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

    public  Exercicio toEntity(ExercicioCreateEditDto exercicioDTO) {
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(exercicioDTO.getNome());
        exercicio.setDescricao(exercicioDTO.getDescricao());

        Midia midia = midiaService.findById(exercicioDTO.getMidiaid());
        exercicio.setMidia(midia);

        return exercicio;
    }
}