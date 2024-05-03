package API.nhyira.apivitalis.Entity;


import jakarta.persistence.*;

@Entity
@Table(name = "rotinaTreino")
public class RotinaTreino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRotinaTreino;

    private String nome;

    private String observacoes;

    public Integer getIdRotinaTreino() {
        return idRotinaTreino;
    }

    public void setIdRotinaTreino(Integer idRotinaTreino) {
        this.idRotinaTreino = idRotinaTreino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
