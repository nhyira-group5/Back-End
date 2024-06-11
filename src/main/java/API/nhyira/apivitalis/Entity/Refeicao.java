package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRefeicao;
    private String nome;
    private String preparo;

    @ManyToOne
    @JoinColumn(name = "midiaId")
    private Midia midiaId;


    @OneToMany(mappedBy = "refeicaoId")
    private List<AlimentoPorRefeicao> alimentoPorRefeicaos;
}
