package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class RotinaSemanal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRotinaSemanal;

    @ManyToOne
    @JoinColumn(name = "rotinaMensalId")
    private RotinaMensal rotinaMensalId;

    @OneToMany(mappedBy = "rotinaSemanalId")
    private List<RotinaDiaria> rotinaDiariaId;

    private Integer numSemana;
    private Integer concluido;
}
