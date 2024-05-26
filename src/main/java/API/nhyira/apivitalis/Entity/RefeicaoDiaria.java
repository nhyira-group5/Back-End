package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RefeicaoDiaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRefeicaoDiaria;


    @ManyToOne
    @JoinColumn(name = "refeicaoId")
    private Refeicao refeicaoId;

    @ManyToOne
    @JoinColumn(name = "rotinaDiariaId")
    private RotinaDiaria rotinaDiariaId;

    private Integer concluido;
}
