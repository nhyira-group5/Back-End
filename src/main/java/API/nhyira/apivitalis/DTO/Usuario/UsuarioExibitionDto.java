package API.nhyira.apivitalis.DTO.Usuario;

import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Usuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioExibitionDto {
    private Integer id;
    private String nickname;
    private String cpf;
    private String nome;
    private LocalDate dtNasc;
    private String sexo;
    private String email;
    private Midia midia;
    private Usuario.TipoUsuario tipo; // Alterado para String
    private UsuarioDto personalId;
    private double imc;
}
