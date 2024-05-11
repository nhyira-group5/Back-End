package API.nhyira.apivitalis.Entity;

import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioTokenDto;
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

    private String nickName;

    private String cpf;

    private String nome;

    private LocalDate dtNasc;

    private String sexo;

    private String email;

    private String senha;

    private TipoUsuario tipo;
    @ManyToOne
    @JoinColumn(name = "fkmidia")
    private Midia midia;

    // Getters e Setters


    public enum TipoUsuario {
        USUARIO,
        PERSONAL
    }


}
