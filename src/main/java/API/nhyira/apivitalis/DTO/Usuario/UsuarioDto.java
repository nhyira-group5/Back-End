package API.nhyira.apivitalis.DTO.Usuario;

import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.Column;
import lombok.Data;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UsuarioDto {
    private Integer idUsuario;

    @Column(nullable = false, unique = true, length = 20)
    @NotBlank(message = "O Nickname de usuário é obrigatório")
    @Size(max = 20, message = "O nome de usuário deve ter no máximo 20 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[@#$%^&+=]).*$", message = "O nickname deve conter pelo menos uma letra maiúscula e um caractere especial")
    private String nickname;

    private String cpf;
    private String nome;
    private LocalDate dtNasc;
    private String sexo;
    private String email;
    private Usuario.TipoUsuario tipo;
    private UsuarioExibitionDto.MidiaDto midiaDto;

}
