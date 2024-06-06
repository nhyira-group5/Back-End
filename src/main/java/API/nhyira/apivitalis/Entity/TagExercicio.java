package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class TagExercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTagExercicio;


    @ManyToOne
    @JoinColumn(name = "tag_id")
    private Tag tag;

    @ManyToOne
    @JoinColumn(name = "exercicioId")
    private Exercicio exercicioId;


}
