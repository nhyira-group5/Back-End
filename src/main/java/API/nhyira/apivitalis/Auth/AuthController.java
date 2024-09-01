package API.nhyira.apivitalis.Auth;


import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioLoginDto;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioTokenDto;
import API.nhyira.apivitalis.Service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UsuarioTokenDto> loginUser(
            @RequestBody @Valid UsuarioLoginDto usuarioLogin
    ) {
        UsuarioTokenDto usuarioToken = this.usuarioService.autenticar(usuarioLogin);
        return ResponseEntity.status(200).body(usuarioToken);
    }
}