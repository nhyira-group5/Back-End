package API.nhyira.Crud.Model;

public class AlimentoModel {

    private Integer idAlimento;
    private String nome;
    private String categoria;
    private float carboidrato;
    private float proteina;
    private float gordura;

    public Integer getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(Integer idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public float getCarboidrato() {
        return carboidrato;
    }

    public void setCarboidrato(float carboidrato) {
        this.carboidrato = carboidrato;
    }

    public float getProteina() {
        return proteina;
    }

    public void setProteina(float proteina) {
        this.proteina = proteina;
    }

    public float getGordura() {
        return gordura;
    }

    public void setGordura(float gordura) {
        this.gordura = gordura;
    }
}
