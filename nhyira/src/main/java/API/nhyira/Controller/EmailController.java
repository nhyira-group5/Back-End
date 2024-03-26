package API.nhyira.Controller;

import API.nhyira.Service.EmailService;
import API.nhyira.Model.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
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
    public ResponseEntity<String> enviarEmail(@RequestBody EmailModel emailRequest) {
        try {
            emailService.enviarEmail(emailRequest);
            return ResponseEntity.ok("E-mail enviado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Falha ao enviar e-mail: " + e.getMessage());
        }
    }
}
