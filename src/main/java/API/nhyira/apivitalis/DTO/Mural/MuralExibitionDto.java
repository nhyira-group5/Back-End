package API.nhyira.apivitalis.DTO.Mural;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.Midia;
import API.nhyira.apivitalis.Entity.Usuario;
import lombok.Data;

import java.time.LocalDate;


@Data
public class MuralExibitionDto {

    private Integer idMural;

    private UsuarioDto usuarioId;

    private Midia midiaId;

    private LocalDate dtPostagem;


    @Data
    public static class UsuarioDto{
        private Integer idUsuario;

        private String nickname;

        private String nome;

        private Usuario.TipoUsuario tipo;

    }
}
