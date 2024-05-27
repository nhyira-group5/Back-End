package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AlimentoPorRefeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlimentoRefeicao;
    private int qtdAlimento;

    @ManyToOne
    @JoinColumn(name = "alimentoId")
    private Alimento alimentoId;

    @ManyToOne
    @JoinColumn(name = "refeicaoId")
    private Refeicao refeicaoId;

    @ManyToOne
    @JoinColumn(name = "metricaId")
    private Metrica metricaId;
}
