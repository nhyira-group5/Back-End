package API.nhyira.Auth.Controller;

import API.nhyira.Auth.DTO.UsuarioLogin;
import API.nhyira.Auth.DTO.UsuarioToken;
import API.nhyira.Auth.Security.AuthEntryPoint;
import API.nhyira.Auth.Service.AuthUsuarioService;
import API.nhyira.Crud.Service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioToken> loginUser(
            @RequestBody UsuarioLogin usuarioLogin
    ) {
        UsuarioToken usuarioToken = usuarioService.autenticar(usuarioLogin);
        return ResponseEntity.status(200).body(usuarioToken);
    }
}
