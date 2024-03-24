package API.nhyira.Controller;

import API.nhyira.Service.EmailService;
import API.nhyira.Model.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/api/email/enviar")
    public ResponseEntity<String> enviarEmail(@RequestBody EmailModel request) {
        try {
            emailService.enviarEmail(request);
            return ResponseEntity.ok("E-mail enviado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Falha ao enviar e-mail: " + e.getMessage());
        }
    }
}
