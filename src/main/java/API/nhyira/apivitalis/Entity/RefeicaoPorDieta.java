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
    private Integer idRefeicaoPorDieta;

    @ManyToOne
    private Dieta dietaId;

    @ManyToOne
    private Refeicao refeicaoId;
}
