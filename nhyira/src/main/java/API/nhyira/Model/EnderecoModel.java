package API.nhyira.Model;

public class EnderecoModel {
    private Integer idEndereco;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private Integer fkPersonal;

    // Getters and Setters
    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getFkPersonal() {
        return fkPersonal;
    }

    public void setFkPersonal(Integer fkPersonal) {
        this.fkPersonal = fkPersonal;
    }
}
