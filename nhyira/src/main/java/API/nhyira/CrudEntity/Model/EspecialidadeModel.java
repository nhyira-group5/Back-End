package API.nhyira.CrudEntity.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "especialidade")
public class EspecialidadeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_especialidade")
    private Integer idEspecialidade;

    @Column(name = "nome", nullable = false, length = 120)
    private String nome;

    public Integer getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(Integer idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
