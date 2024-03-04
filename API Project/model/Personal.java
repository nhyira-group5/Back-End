import org.springframework.http.ResponseEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String telefone;
    private String endereco;
    private String especialidade;


    public ResponseEntity<Long> getId() {
        return ResponseEntity.ok(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ResponseEntity<String> getNome() {
        return ResponseEntity.ok(nome);
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public ResponseEntity<String> getEmail() {
        return ResponseEntity.ok(email);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ResponseEntity<String> getSenha() {
        return ResponseEntity.ok(senha);
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public ResponseEntity<String> getCpf() {
        return ResponseEntity.ok(cpf);
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public ResponseEntity<String> getTelefone() {
        return ResponseEntity.ok(telefone);
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public ResponseEntity<String> getEndereco() {
        return ResponseEntity.ok(endereco);
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public ResponseEntity<String> getEspecialidade() {
        return ResponseEntity.ok(especialidade);
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
