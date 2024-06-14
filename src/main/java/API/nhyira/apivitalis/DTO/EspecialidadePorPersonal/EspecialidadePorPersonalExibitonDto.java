package API.nhyira.apivitalis.DTO.EspecialidadePorPersonal;

import API.nhyira.apivitalis.DTO.Especialidade.EspecialidadeExibitionDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import lombok.Data;

@Data
public class EspecialidadePorPersonalExibitonDto {
    private int idEspecialidade;
    private UsuarioDto personalId;
    private EspecialidadeExibitionDto especialidadeId;
}
