package API.nhyira.apivitalis.DTO.Usuario;


import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Data
public class UsuarioDto {
    private Integer idUsuario;

    @NotBlank(message = "O nome de usuário é obrigatório")
    @Size(max = 20, message = "O nome de usuário deve ter no máximo 20 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "O nome de usuário deve conter pelo menos uma letra maiúscula e um caractere especial")
    private String nickname;

    private String cpf;
    private String nome;
    private LocalDate dtNasc;
    private String sexo;
    private String email;
    private Usuario.TipoUsuario tipo;
}
