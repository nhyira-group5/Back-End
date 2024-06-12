package API.nhyira.apivitalis.DTO.RotinaUsuario;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.Meta;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RotinaUsuarioCreateEditDto {


    @NotNull
    private Integer usuarioId;

    @NotNull
    private Integer metaId;



}
