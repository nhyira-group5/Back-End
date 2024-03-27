package API.nhyira.CrudEntity.Model;

public class AlimentoRefeicaoModel {

    private Integer idAlimentoRefeicao;
    private String papelAlimento;
    private RefeicaoModel refeicao;
    private AlimentoModel alimento;

    public Integer getIdAlimentoRefeicao() {
        return idAlimentoRefeicao;
    }

    public void setIdAlimentoRefeicao(Integer idAlimentoRefeicao) {
        this.idAlimentoRefeicao = idAlimentoRefeicao;
    }

    public String getPapelAlimento() {
        return papelAlimento;
    }

    public void setPapelAlimento(String papelAlimento) {
        this.papelAlimento = papelAlimento;
    }

    public RefeicaoModel getRefeicao() {
        return refeicao;
    }

    public void setRefeicao(RefeicaoModel refeicao) {
        this.refeicao = refeicao;
    }

    public AlimentoModel getAlimento() {
        return alimento;
    }

    public void setAlimento(AlimentoModel alimento) {
        this.alimento = alimento;
    }
}
