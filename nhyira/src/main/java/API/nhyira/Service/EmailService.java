package API.nhyira.Service;

import API.nhyira.Model.EmailModel;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
}

