package API.nhyira.apivitalis.DTO.Usuario;

import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class UsuarioExibitionDto {

    @Getter
    private Integer id;
    private String nickname;
    private String cpf;
    private String nome;
    private LocalDate dtNasc;
    private String sexo;
    private String email;

    private Midia midia;

    private Usuario.TipoUsuario tipo; // Alterado para String
    private double imc;

}
