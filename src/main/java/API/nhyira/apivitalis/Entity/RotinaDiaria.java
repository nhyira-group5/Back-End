package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class RotinaDiaria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRotinaDiaria;

    @ManyToOne
    @JoinColumn(name = "rotinaSemanalId")
    private RotinaSemanal rotinaSemanalId;

    @OneToMany(mappedBy = "rotinaDiariaId")
    private List<Treino> treinoId;

    private Integer dia;

    @OneToMany(mappedBy = "rotinaDiariaId")
    private List<RefeicaoDiaria> refeicaoDiariaId;

    private Integer concluido;
}
