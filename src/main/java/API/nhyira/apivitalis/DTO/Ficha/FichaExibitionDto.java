package API.nhyira.apivitalis.DTO.Ficha;

import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.mail.event.MailEvent;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FichaExibitionDto {


    private Integer id;


    private Integer bebe;

    private Integer fuma;
    private String deficiencias;
    private String problemasCaridiacos;
    private String doencasRespiratorias;
    private Float peso;
    private float altura;

    private Meta metaId;

    private Usuario usuarioId;

}
