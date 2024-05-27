package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    private String nickname;
    private String cpf;
    private String nome;
    private LocalDate dtNasc;
    private String sexo;
    private String email;
    private String senha;
    private TipoUsuario tipo;

    @ManyToOne
    @JoinColumn(name = "midiaId")
    private Midia midiaId;

    public enum TipoUsuario {
        USUARIO,
        PERSONAL,
    }
}
