package API.nhyira.Model;

import java.sql.Timestamp;

public class RotinaUsuarioModel {
    private Integer idRotinaUsuario;
    private Timestamp dtRotina;
    private Integer fkTreinoMeta;
    private Integer fkDieta;
    private Integer fkUsuario;


    public Integer getIdRotinaUsuario() {
        return idRotinaUsuario;
    }

    public void setIdRotinaUsuario(Integer idRotinaUsuario) {
        this.idRotinaUsuario = idRotinaUsuario;
    }

    public Timestamp getDtRotina() {
        return dtRotina;
    }

    public void setDtRotina(Timestamp dtRotina) {
        this.dtRotina = dtRotina;
    }

    public Integer getFkTreinoMeta() {
        return fkTreinoMeta;
    }

    public void setFkTreinoMeta(Integer fkTreinoMeta) {
        this.fkTreinoMeta = fkTreinoMeta;
    }

    public Integer getFkDieta() {
        return fkDieta;
    }

    public void setFkDieta(Integer fkDieta) {
        this.fkDieta = fkDieta;
    }

    public Integer getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Integer fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}