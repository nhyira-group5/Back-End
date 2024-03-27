package API.nhyira.CrudEntity.Model;

public class ReclamacaoModel {
    private Integer idReclamacao;
    private String topico;
    private String descricao;
    private Integer fkPersonal;
    private Integer fkUsuario;

    // Getters and Setters
    public Integer getIdReclamacao() {
        return idReclamacao;
    }

    public void setIdReclamacao(Integer idReclamacao) {
        this.idReclamacao = idReclamacao;
    }

    public String getTopico() {
        return topico;
    }

    public void setTopico(String topico) {
        this.topico = topico;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getFkPersonal() {
        return fkPersonal;
    }

    public void setFkPersonal(Integer fkPersonal) {
        this.fkPersonal = fkPersonal;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}

