package API.nhyira.apivitalis.Entity;


import API.nhyira.apivitalis.DTO.Exercicio.TagExercicioDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Exercicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idExercicio;


    @OneToMany(mappedBy = "exercicioId")
    private List<Midia> midia;

    private String nome;

    private String descricao;

    @OneToMany(mappedBy = "exercicioId")
    private List<TagExercicio> tagExercicios;


}
