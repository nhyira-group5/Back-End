package API.nhyira.apivitalis.Entity;


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

    private Integer IdExercicio;


    @ManyToOne
    @JoinColumn (name = "midia_id")
    private Midia midia;

    private String nome;

    private String descricao;
//
//    @OneToMany(mappedBy = "exercicioId")
//    private List<Treino> treinoList;


}
