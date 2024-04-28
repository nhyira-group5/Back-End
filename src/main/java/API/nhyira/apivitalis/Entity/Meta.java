package API.nhyira.apivitalis.Entity;

import jakarta.persistence.*;

@Entity
public class Meta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_meta")
    private Integer idMeta;

    @Column(name = "meta")
    private String meta;

    @Column(name = "qtd_fase")
    private int qtdFase;

    public Integer getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Integer idMeta) {
        this.idMeta = idMeta;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public int getQtdFase() {
        return qtdFase;
    }

    public void setQtdFase(int qtdFase) {
        this.qtdFase = qtdFase;
    }
}
