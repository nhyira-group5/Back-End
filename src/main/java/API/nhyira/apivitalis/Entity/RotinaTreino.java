package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
//@Table(name = "rotinaTreino")
public class RotinaTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRotinaTreino;

    private String nome;

    private String observacoes;

}
