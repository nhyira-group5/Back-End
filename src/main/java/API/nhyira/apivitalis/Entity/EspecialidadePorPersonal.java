package API.nhyira.apivitalis.Entity;


import API.nhyira.apivitalis.DTO.Especialidade.EspecialidadeMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class EspecialidadePorPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEspecialidade;

    @ManyToOne
    @JoinColumn(name = "personalId")
    private Usuario personalId;


    @ManyToOne
    @JoinColumn(name = "especialidadeId")
    private Especialidade especialidadeId;

}

