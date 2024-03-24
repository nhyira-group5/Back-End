package API.nhyira.Service;

import API.nhyira.Model.EmailModel;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void enviarEmail(EmailModel emailModel) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(emailModel.getDestinatario());
            helper.setSubject(emailModel.getAssunto());
            helper.setText(emailModel.getConteudo(), true);
            javaMailSender.send(message);
        } catch (jakarta.mail.MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail", e);
        }
    }

    // Getters e Setters
    public JavaMailSender getJavaMailSender() {
        return javaMailSender;
    }
}

