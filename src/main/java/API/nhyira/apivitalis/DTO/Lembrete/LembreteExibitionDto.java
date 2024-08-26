package API.nhyira.apivitalis.DTO.Lembrete;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class LembreteExibitionDto {

    private Integer id;

    private String conteudo;


    private LocalDateTime dataLembrete;



}
