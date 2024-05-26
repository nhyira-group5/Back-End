package API.nhyira.apivitalis.DTO.Exercicio;

import API.nhyira.apivitalis.Entity.Exercicio;
import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Service.MidiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    public Exercicio toEntity(ExercicioCreateEditDto exercicioDTO) {
        Exercicio exercicio = new Exercicio();
        exercicio.setNome(exercicioDTO.getNome());
        exercicio.setDescricao(exercicioDTO.getDescricao());

        Midia midia = midiaService.findById(exercicioDTO.getMidiaid())
                .orElseThrow(() -> new RuntimeException("Midia não encontrada com o id: " + exercicioDTO.getMidiaid()));
        exercicio.setMidia(midia);

        return exercicio;
    }
}