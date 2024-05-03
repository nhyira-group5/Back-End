package API.nhyira.apivitalis.EmailSender.Service;

import API.nhyira.apivitalis.EmailSender.Model.EmailModel;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {

    public void enviarEmail(EmailModel email) {
        final String remetente = "VitalisNhyiraSoftware@outlook.com";
        final String senha = "salada123";

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(remetente, senha);
                    }
                });

        try {
            MimeMessage mensagem = new MimeMessage(session);
            mensagem.setFrom(new InternetAddress(remetente));
            mensagem.addRecipient(Message.RecipientType.TO, new InternetAddress(email.getDestinatario()));
            mensagem.setSubject(email.getAssunto());
            mensagem.setText(email.getConteudo());

            Transport.send(mensagem);
            System.out.println("Email enviado com sucesso!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean validarEmail(String email) {
        return email != null && !email.isEmpty() && email.matches("^(.+)@(.+)$") && email.length() <= 100;
    }

}
