package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RefeicaoPorDieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRefeicaoDieta;

    @ManyToOne
    @JoinColumn(name = "dietaId")
    private Dieta dietaId;

    @ManyToOne
    @JoinColumn(name = "refeicaoId")
    private Refeicao refeicaoId;
}
