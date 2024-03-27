package API.nhyira.EmailSender.Service;

import API.nhyira.EmailSender.Model.EmailModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    public void enviarEmail (EmailModel email) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setFrom("contato.nhyira@gmail.com");
        mensagem.setTo(email.getDestinatario());

        mensagem.setSubject(email.getAssunto());
        mensagem.setText(email.getConteudo());

        emailSender.send(mensagem);
        System.out.println("Email enviado com sucesso!");
    }

    public Boolean validarEmail(String email) {
        return email != null && !email.isEmpty() && email.matches("^(.+)@(.+)$") && email.length() <= 100;
    }
}

