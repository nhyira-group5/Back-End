package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Exercicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_exercicio")
    private Integer IdExercicio;

    @JoinColumn (name = "midia_id")
    private String midiaUrl;


    @Column(name = "nome")
    private String nome;


    private String descricao;
}