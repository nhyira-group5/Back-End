package API.nhyira.CrudEntity.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "meta")
public class MetaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_meta")
    private Integer idMeta;

    @Column(name = "meta", nullable = false, length = 100)
    private String meta;

    @Column(name = "qtd_fase", nullable = false)
    private int qtdFase;

    // Getters e Setters

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
