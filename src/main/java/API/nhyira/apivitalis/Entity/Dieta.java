package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Dieta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDieta;
    private String nome;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "metaId")
    private Meta metaId;
}
