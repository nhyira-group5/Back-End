package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class EspecialidadePorMeta {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspecialidadeMeta;

    @ManyToOne
    @JoinColumn(name = "especialidadeId")
    private Especialidade especialidadeId;

    @ManyToOne
    @JoinColumn(name = "metaId")
    private Meta metaId;


}

