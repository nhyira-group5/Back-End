package API.nhyira.apivitalis.DTO.Mural;

import API.nhyira.apivitalis.Entity.Usuario;
import lombok.Data;
import java.time.LocalDate;

@Data
public class MuralExibitionDto {
    private Integer idMural;
    private UsuarioDto usuarioId;
    private MidiaDto midia;
    private LocalDate dtPostagem;

    @Data
    public static class UsuarioDto{
        private Integer idUsuario;
        private String nickname;
        private String nome;
        private Usuario.TipoUsuario tipo;
    }

    @Data
    public static class MidiaDto{
        private Integer id;
        private String nome;
        private String caminho;
        private String extensao;
        private String tipo;
    }
}
