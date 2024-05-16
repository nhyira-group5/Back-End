package API.nhyira.apivitalis.DTO.RotinaUsuario;


import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RotinaUsuarioExibitionDto {

    private Integer idRotinaUsuario;

    private UsuarioDto usuarioId;

    private Meta metaId;

}
