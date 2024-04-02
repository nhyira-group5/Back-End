package API.nhyira.Crud.Model;


public class PontuacaoModel {
    private Integer idPontuacao;
    private int pontos;
    private int sequenciaSemanal;
    private Integer fkUsuario;

    // Getters and Setters
    public Integer getIdPontuacao() {
        return idPontuacao;
    }

    public void setIdPontuacao(Integer idPontuacao) {
        this.idPontuacao = idPontuacao;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getSequenciaSemanal() {
        return sequenciaSemanal;
    }

    public void setSequenciaSemanal(int sequenciaSemanal) {
        this.sequenciaSemanal = sequenciaSemanal;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}