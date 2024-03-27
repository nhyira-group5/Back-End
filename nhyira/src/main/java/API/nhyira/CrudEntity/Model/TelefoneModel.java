package API.nhyira.CrudEntity.Model;

public class TelefoneModel {
    private Integer idTelefone;
    private String numero;
    private boolean ativo;
    private Integer fkUsuario;
    private Integer fkPersonal;

    // Getters and Setters
    public Integer getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(Integer idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Integer getFkPersonal() {
        return fkPersonal;
    }

    public void setFkPersonal(Integer fkPersonal) {
        this.fkPersonal = fkPersonal;
    }
}
