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
        final String remetente = "NhyraFive@outlook.com";
        final String senha = "FiveNhyra";

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
            mensagem.setSubject("Bem-vindo à Nhyira!");


            String conteudo = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body {font-family: Arial, sans-serif; background-color: #48B75A; margin: 0; padding: 0;}" +
                    ".container { max-width: 600px; margin: auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);}" +
                    ".welcome {color: #1A1A1A; font-size: 24px; font-weight: bold; text-align: center;}" +
                    ".message {color: #1A1A1A; font-size: 18px; text-align: center;}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<h1 class='welcome'>Bem-vindo a Nhyira!</h1>" +
                    "<p class='message'>Ola, voce foi cadastrado com sucesso no sistema da Vitalis. Aproveite para compartilhar com seus colegas.</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            mensagem.setContent(conteudo, "text/html");

            Transport.send(mensagem);
            System.out.println("Email enviado com sucesso!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean validarEmail(String email) {
        return email != null && !email.isEmpty() && email.matches("^(.+)@(.+)$") && email.length() <= 100;
    }

    public void enviarEmailPagamento(String destinatario, String assunto, String nomeUsuario) {
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
            mensagem.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
            mensagem.setSubject(assunto);

            String conteudo = "<html>" +
                    "<head>" +
                    "<style>" +
                    "body {font-family: Arial, sans-serif; background-color: #48B75A; margin: 0; padding: 0;}" +
                    ".container { max-width: 600px; margin: auto; padding: 20px; background-color: #fff; border-radius: 10px; box-shadow: 0px 0px 10px 0px rgba(0,0,0,0.1);}" +
                    ".welcome {color: #1A1A1A; font-size: 24px; font-weight: bold; text-align: center;}" +
                    ".message {color: #1A1A1A; font-size: 18px; text-align: center;}" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<div class='container'>" +
                    "<h1 class='welcome'>Pagamento Aprovado</h1>" +
                    "<p class='message'>Olá " + nomeUsuario + ", seu pagamento foi aprovado com sucesso.</p>" +
                    "</div>" +
                    "</body>" +
                    "</html>";

            mensagem.setContent(conteudo, "text/html");

            Transport.send(mensagem);
            System.out.println("Email de pagamento enviado com sucesso!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }


}
