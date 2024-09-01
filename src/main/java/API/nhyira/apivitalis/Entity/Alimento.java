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
public class Alimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAlimento;
    private String nome;
    private Double carboidrato;
    private Double proteina;
    private Double gordura;

    @OneToMany(mappedBy = "alimentoId")
    private List<Midia> midiaId;
}
