package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class RotinaDiario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTreinoDiario;


    @ManyToOne
    @JoinColumn(name = "exercicioId")
    private Exercicio exercicioId;


    @ManyToOne
    @JoinColumn(name = "rotinaSemanalId")
    private RotinaSemanal rotinaSemanalId;

    private Integer concluido;

}
