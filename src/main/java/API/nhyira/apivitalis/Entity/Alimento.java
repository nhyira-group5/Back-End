package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlimento;
    private String nome;
    private Double carboidrato;
    private Double proteina;
    private Double gordura;

    @ManyToOne
    @JoinColumn(name = "midiaId")
    private Midia midiaId;
}
