package API.nhyira.apivitalis.EmailSender.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EmailModel{
    private String destinatario;
    private String assunto;
    private String conteudo;

}