package API.nhyira.CrudEntity.Model;

public class TreinoPorMetaModel {
    private Integer idTreinoMeta;
    private Integer fkTreino;
    private Integer fkMeta;

    // Getters and Setters
    public Integer getIdTreinoMeta() {
        return idTreinoMeta;
    }

    public void setIdTreinoMeta(Integer idTreinoMeta) {
        this.idTreinoMeta = idTreinoMeta;
    }

    public Integer getFkTreino() {
        return fkTreino;
    }

    public void setFkTreino(Integer fkTreino) {
        this.fkTreino = fkTreino;
    }

    public Integer getFkMeta() {
        return fkMeta;
    }

    public void setFkMeta(Integer fkMeta) {
        this.fkMeta = fkMeta;
    }
}