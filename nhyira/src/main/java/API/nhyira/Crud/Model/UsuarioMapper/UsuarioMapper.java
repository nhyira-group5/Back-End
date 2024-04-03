package API.nhyira.Crud.Model.UsuarioMapper;

import API.nhyira.Auth.DTO.DetailsUsuario;
import API.nhyira.Auth.DTO.UsuarioToken;
import API.nhyira.Crud.Model.UsuarioModel;

public class UsuarioMapper {
    public static UsuarioToken of(UsuarioModel usuarioModel, String token) {
        UsuarioToken usuario = new UsuarioToken();

        usuario.setEmail(usuarioModel.getEmail());
        usuario.setUsername(usuarioModel.getUsername());
        usuario.setToken(token);

        return usuario;
    }
}
