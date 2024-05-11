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

    private String deficiencias;

    private String problemasCaridiacos;

    private String doencasRespiratorias;

    private Float peso;

    private float altura;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private Usuario usuarioId;

    @ManyToOne
    @JoinColumn(name = "metaId")
    private Meta metaId;

}
