package API.nhyira.apivitalis.DTO.RotinaUsuario;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RotinaUsuarioCreateEditDto {
    @NotNull
    private Integer usuarioId;

    @NotNull
    private Integer metaId;

    @NotNull
    private Integer rotinaAlternativa;
}