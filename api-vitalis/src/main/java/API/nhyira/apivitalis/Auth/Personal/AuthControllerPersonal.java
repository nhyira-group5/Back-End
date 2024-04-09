package API.nhyira.apivitalis.Auth.Personal;

import API.nhyira.apivitalis.Auth.Personal.DTO.PersonalLoginDto;
import API.nhyira.apivitalis.Auth.Personal.DTO.PersonalTokenDto;
import API.nhyira.apivitalis.Service.PersonalService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class AuthControllerPersonal {
    @Autowired
    private PersonalService personalService;

    @PostMapping("/personal")
    public ResponseEntity<PersonalTokenDto> loginToken(@RequestParam @Valid PersonalLoginDto personalLogin){

        PersonalTokenDto personalToken = personalService.autenticar(personalLogin);
        return ResponseEntity.status(200).body(personalToken);

    }

}
