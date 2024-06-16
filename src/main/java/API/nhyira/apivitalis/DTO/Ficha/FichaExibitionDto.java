package API.nhyira.apivitalis.DTO.Ficha;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Usuario;
import jakarta.mail.event.MailEvent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class FichaExibitionDto {
    private Integer id;
    private Integer problemaCardiaco;
    private Integer dorPeitoAtividade;
    private Integer dorPeitoUltimoMes;
    private Integer problemaOsseoArticular;
    private Integer medicamentoPressaoCoracao;
    private Integer impedimentoAtividade;
    private Float peso;
    private float altura;
    private UsuarioDto usuarioId;
    private double IMC;
}
