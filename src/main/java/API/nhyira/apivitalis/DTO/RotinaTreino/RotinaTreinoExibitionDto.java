package API.nhyira.apivitalis.DTO.RotinaTreino;

public class RotinaTreinoExibitionDto {

    private Integer idRotinaTreino;
    private String nome;

    private String observacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Integer getIdRotinaTreino() {
        return idRotinaTreino;
    }

    public void setIdRotinaTreino(Integer idRotinaTreino) {
        this.idRotinaTreino = idRotinaTreino;
    }
}
