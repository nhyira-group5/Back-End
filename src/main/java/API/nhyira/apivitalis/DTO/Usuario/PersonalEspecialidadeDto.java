package API.nhyira.apivitalis.DTO.Usuario;


import API.nhyira.apivitalis.DTO.Especialidade.EspecialidadeExibitionDto;
import API.nhyira.apivitalis.DTO.EspecialidadePorPersonal.EspecialidadePorPersonalExibitonDto;
import API.nhyira.apivitalis.DTO.Refeicao.RefeicaoExibitionDto;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.Column;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
public class PersonalEspecialidadeDto {

    private Integer idPersonal;
    private String nickname;
    private String cpf;
    private String nome;
    private LocalDate dtNasc;
    private String sexo;
    private String email;
    private Usuario.TipoUsuario tipo;
    private List<EspecialidadePorPersonalDto> exibitonDto;
    private RefeicaoExibitionDto.MidiaDto midiaDto;

    @Data
    public static class EspecialidadePorPersonalDto{
        private Integer id;
        private EspecialidadeExibitionDto especialidadeId;
    }
}
