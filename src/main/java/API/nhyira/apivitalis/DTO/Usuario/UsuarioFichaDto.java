package API.nhyira.apivitalis.DTO.Usuario;

import API.nhyira.apivitalis.Entity.Usuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioFichaDto {
    private Integer idUsuario;
    private String nickname;
    private String cpf;
    private String nome;
    private LocalDate dtNasc;
    private String sexo;
    private String email;
    private Usuario.TipoUsuario tipo;
    private double IMC;
}
