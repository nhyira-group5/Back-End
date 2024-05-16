package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Ficha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idFicha;

    private Integer bebe;

    private Integer fuma;

    private Integer problemasCardiacos;

    private Integer dorPeitoAtividade;

    private Integer dorPeitoUltimoMes;

    private Integer perdaConsiencia;

    private Integer problemaOsseoArticular;

    private Integer medicamentoPressaoCoracao;

    private Integer impedimentoAtividade;

    private Float peso;

    private float altura;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuarioId;

    @ManyToOne
    @JoinColumn(name = "metaId")
    private Meta metaId;

}
