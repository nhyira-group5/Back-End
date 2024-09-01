package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
public class Midia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMidia;

    @ManyToOne
    @JoinColumn(name = "alimento_id")
    private Alimento alimentoId;

    @ManyToOne
    @JoinColumn(name = "exercicio_id")
    private Exercicio exercicioId;

    @ManyToOne
    @JoinColumn(name = "refeicao_id")
    private Refeicao refeicaoId;

    private String nome;
    private String caminho;
    private String extensao;
    private String tipo;
}
