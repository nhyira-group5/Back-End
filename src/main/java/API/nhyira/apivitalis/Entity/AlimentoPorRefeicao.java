package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AlimentoPorRefeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlimentoPorRefeicao;
    private int qtdAlimento;

    @ManyToOne
    private Alimento alimento;

    @ManyToOne
    private Refeicao refeicao;

    @ManyToOne
    private Metrica metrica;
}
