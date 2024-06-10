package API.nhyira.apivitalis.EmailSender.Model;


import lombok.Data;


@Data
public class EmailModel{
    private String destinatario;
    private String assunto;
    private String conteudo;

    public EmailModel(String destinatario, String assunto, String conteudo) {
        this.destinatario = destinatario;
        this.assunto = assunto;
        this.conteudo = conteudo;
    }

}