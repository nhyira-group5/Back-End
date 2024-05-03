package API.nhyira.apivitalis.DTO.RotinaTreino;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RotinaTreinoCreateEditDto {

    @NotBlank
    @Size(max = 100)
    private String nome;

    @Size(max = 250)
    private String observacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
