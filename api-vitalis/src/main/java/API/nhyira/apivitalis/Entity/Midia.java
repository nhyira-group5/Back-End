package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;

public class Midia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMidia;

    private String nomeArquivo;

    private byte[] conteudo;

    private String extensao;
}
