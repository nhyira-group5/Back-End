package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@Entity
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTreino;


    @ManyToOne
    @JoinColumn(name = "exercicioId")
    private Exercicio exercicioId;


    @ManyToOne
    @JoinColumn(name = "rotinaDiariaId")
    private RotinaDiaria rotinaDiariaId;

    private Integer serie;

    private Integer repeticao;

    private LocalTime tempo;

    private Integer concluido;

}
