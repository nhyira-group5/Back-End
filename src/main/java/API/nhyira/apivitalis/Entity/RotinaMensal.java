package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RotinaMensal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRotinaMensal;

    @ManyToOne
    @JoinColumn(name = "rotinaUsuarioId")
    private RotinaUsuario rotinaUsuarioId;

    private String mes;

    private int Ano;
}
