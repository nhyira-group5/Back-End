package API.nhyira.EmailSender.Controller;

import API.nhyira.EmailSender.Service.EmailService;
import API.nhyira.EmailSender.Model.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public ResponseEntity<String> enviarEmail(
            @RequestBody EmailModel emailRequest
    ) {
        if (!emailService.validarEmail(emailRequest.getDestinatario())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Inválido!");
        }

        try {
            emailService.enviarEmail(emailRequest);
            return ResponseEntity.ok("E-mail enviado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao enviar e-mail: " + e.getMessage());
        }
    }
}
