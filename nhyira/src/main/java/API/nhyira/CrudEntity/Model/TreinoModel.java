package API.nhyira.CrudEntity.Model;
import java.sql.Time;
public class TreinoModel {
    private Integer idTreino;
    private String nome;
    private String descricao;
    private int repeticao;
    private int serie;
    private Time tempo;
    private Integer fkImagemTreino;
    private Integer fkImagemTreino2;


    public Integer getIdTreino() {
        return idTreino;
    }

    public void setIdTreino(Integer idTreino) {
        this.idTreino = idTreino;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getRepeticao() {
        return repeticao;
    }

    public void setRepeticao(int repeticao) {
        this.repeticao = repeticao;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public Time getTempo() {
        return tempo;
    }

    public void setTempo(Time tempo) {
        this.tempo = tempo;
    }

    public Integer getFkImagemTreino() {
        return fkImagemTreino;
    }

    public void setFkImagemTreino(Integer fkImagemTreino) {
        this.fkImagemTreino = fkImagemTreino;
    }

    public Integer getFkImagemTreino2() {
        return fkImagemTreino2;
    }

    public void setFkImagemTreino2(Integer fkImagemTreino2) {
        this.fkImagemTreino2 = fkImagemTreino2;
    }
}
