package API.nhyira.apivitalis.Auth;

import API.nhyira.apivitalis.Auth.Personal.DTO.PersonalLoginDto;
import API.nhyira.apivitalis.Auth.Personal.DTO.PersonalTokenDto;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioLoginDto;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioTokenDto;
import API.nhyira.apivitalis.Service.PersonalService;
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

    @Autowired
    private PersonalService personalService;

    @PostMapping("/usuario")
    public ResponseEntity<UsuarioTokenDto> loginUser(
            @RequestBody @Valid UsuarioLoginDto usuarioLogin
    ) {
        UsuarioTokenDto usuarioToken = usuarioService.autenticar(usuarioLogin);
        return ResponseEntity.status(200).body(usuarioToken);
    }

    @PostMapping("/personal")
    public ResponseEntity<PersonalTokenDto> loginPersonal(
            @RequestBody @Valid PersonalLoginDto personalLogin
    ) {
        PersonalTokenDto personalToken = personalService.autenticar(personalLogin);
        return ResponseEntity.status(200).body(personalToken);
    }
}
