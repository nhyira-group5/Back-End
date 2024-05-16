package API.nhyira.apivitalis.DTO.Exercicio;

import API.nhyira.apivitalis.Entity.Exercicio;
import org.springframework.stereotype.Component;

@Component
public class ExercicioMapper {

    public Exercicio toEntity(ExercicioCreateEditDto dto) {
        Exercicio exercicio = new Exercicio();
        exercicio.setMidiaUrl(dto.getMidiaUrl());
        exercicio.setNome(dto.getNome());
        exercicio.setDescricao(dto.getDescricao());
        return exercicio;
    }

    public ExercicioExibitionDto toDTO(Exercicio exercicio) {
        return new ExercicioExibitionDto(
                exercicio.getIdExercicio(),
                exercicio.getMidiaUrl(),
                exercicio.getNome(),
                exercicio.getDescricao()
        );
    }
}