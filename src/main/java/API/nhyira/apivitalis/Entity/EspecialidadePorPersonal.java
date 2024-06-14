package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EspecialidadePorPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspecialidadePersonal;

    @ManyToOne
    @JoinColumn(name = "personalId")
    private Usuario personalId;


    @ManyToOne
    @JoinColumn(name = "especialidadeId")
    private Especialidade especialidadeId;
}

