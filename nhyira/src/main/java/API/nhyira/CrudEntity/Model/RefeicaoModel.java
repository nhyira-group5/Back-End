package API.nhyira.CrudEntity.Model;

public class RefeicaoModel {

    private Integer idRefeicao;
    private String nome;
    private String descricao;
    private DietaModel dieta;

    public Integer getIdRefeicao() {
        return idRefeicao;
    }

    public void setIdRefeicao(Integer idRefeicao) {
        this.idRefeicao = idRefeicao;
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

    public DietaModel getDieta() {
        return dieta;
    }

    public void setDieta(DietaModel dieta) {
        this.dieta = dieta;
    }
}
