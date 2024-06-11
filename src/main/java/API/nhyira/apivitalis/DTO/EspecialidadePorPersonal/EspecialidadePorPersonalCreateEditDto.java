package API.nhyira.apivitalis.DTO.EspecialidadePorPersonal;

import API.nhyira.apivitalis.Entity.Especialidade;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
@Getter
@Setter
public class EspecialidadePorPersonalCreateEditDto {

    @NotNull
    private int personalId;

    @NotNull
    private int especialidadeId;



}
