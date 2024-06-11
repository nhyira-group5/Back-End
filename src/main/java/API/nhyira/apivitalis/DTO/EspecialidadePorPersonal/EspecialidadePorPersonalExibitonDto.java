package API.nhyira.apivitalis.DTO.EspecialidadePorPersonal;

import API.nhyira.apivitalis.DTO.Especialidade.EspecialidadeExibitionDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.Especialidade;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EspecialidadePorPersonalExibitonDto {

    private int idEspecialidade;


    private UsuarioDto personalId;


    private EspecialidadeExibitionDto especialidadeId;



}
