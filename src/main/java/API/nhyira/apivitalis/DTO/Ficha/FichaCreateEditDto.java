package API.nhyira.apivitalis.DTO.Ficha;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FichaCreateEditDto {

    @NotNull
    private Integer bebe;
    @NotNull
    private Integer fuma;

    @Size(max = 100, message = "Maximo de caracteres atingidos")
    private String deficiencias;

    @Size(max = 100, message = "Maximo de caracteres atingidos")
    private String problemasCaridiacos;

    @Size(max = 100, message = "Maximo de caracteres atingidos")
    private String doencasRespiratorias;
    @DecimalMin(value = "0", inclusive = true, message = "O peso deve ser um número positivo ou zero")
    private Float peso;

    @DecimalMin(value = "0", inclusive = true, message = "O altura deve ser um número positivo ou zero")
    private float altura;

    @NotBlank
    @Size(max = 30)
    private String meta;

    private Integer usuarioId;

    public Integer getBebe() {
        return bebe;
    }



    public void setBebe(Integer bebe) {
        this.bebe = bebe;
    }

    public Integer getFuma() {
        return fuma;
    }

    public void setFuma(Integer fuma) {
        this.fuma = fuma;
    }

    public String getDeficiencias() {
        return deficiencias;
    }

    public void setDeficiencias(String deficiencias) {
        this.deficiencias = deficiencias;
    }

    public String getProblemasCaridiacos() {
        return problemasCaridiacos;
    }

    public void setProblemasCaridiacos(String problemasCaridiacos) {
        this.problemasCaridiacos = problemasCaridiacos;
    }

    public String getDoencasRespiratorias() {
        return doencasRespiratorias;
    }

    public void setDoencasRespiratorias(String doencasRespiratorias) {
        this.doencasRespiratorias = doencasRespiratorias;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public String getMeta() {
        return meta;
    }

    public void setMeta(String meta) {
        this.meta = meta;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }
}
