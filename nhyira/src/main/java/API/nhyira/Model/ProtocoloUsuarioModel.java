package API.nhyira.Model;

public class ProtocoloUsuarioModel {
    private Integer idProtocolo;
    private Integer fkUsuario;

    // Getters and Setters
    public Integer getIdProtocolo() {
        return idProtocolo;
    }

    public void setIdProtocolo(Integer idProtocolo) {
        this.idProtocolo = idProtocolo;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
