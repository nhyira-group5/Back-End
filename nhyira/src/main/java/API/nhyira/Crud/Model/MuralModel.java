package API.nhyira.Crud.Model;

import java.sql.Timestamp;

public class MuralModel {
    private Integer idMural;
    private Timestamp dtPostagem;
    private Integer fkUsuario;
    private Integer fkMidia;


    public Integer getIdMural() {
        return idMural;
    }

    public void setIdMural(Integer idMural) {
        this.idMural = idMural;
    }

    public Timestamp getDtPostagem() {
        return dtPostagem;
    }

    public void setDtPostagem(Timestamp dtPostagem) {
        this.dtPostagem = dtPostagem;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Integer getFkMidia() {
        return fkMidia;
    }

    public void setFkMidia(Integer fkMidia) {
        this.fkMidia = fkMidia;
    }
}
