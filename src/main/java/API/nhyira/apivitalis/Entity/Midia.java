package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Midia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idmidia")
    private Integer idMidia;

    @Column(name = "nome")
    private String nomeArquivo;

    @Column(name = "conteudo")
    private byte[] conteudo;

    @Column(name = "extensao")
    private String extensao;

}
