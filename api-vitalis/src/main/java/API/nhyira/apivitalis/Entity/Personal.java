package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;

import java.util.Date;

public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersonal;

     private String username;


    private String cpf;

    private String nome;

    private Date dtNasc;

    private String genero;

    private String email;


    private String email2;

    private String senha;

    private Midia midia;
}
