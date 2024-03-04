import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ServicoValidacaoUsuario {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${google.places.api.key}")
    private String googlePlacesApiKey;

    public ResponseEntity<String> validarId(Long id) {
        if (id == null || id <= 0) {
            return ResponseEntity.badRequest().body("ID inválido");
        }
        return ResponseEntity.ok("ID válido");
    }

    public ResponseEntity<String> validarNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            return ResponseEntity.badRequest().body("Nome inválido");
        }
        return ResponseEntity.ok("Nome válido");
    }

    public ResponseEntity<String> validarEmail(String email) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            return ResponseEntity.badRequest().body("Email inválido");
        }
        return ResponseEntity.ok("Email válido");
    }

    public ResponseEntity<String> validarSenha(String senha) {
        if (senha == null || senha.length() < 8) {
            return ResponseEntity.badRequest().body("A senha deve ter pelo menos 8 caracteres");
        }

        Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$");
        Matcher matcher = pattern.matcher(senha);
        if (!matcher.matches()) {
            return ResponseEntity.badRequest().body("A senha deve conter pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial");
        }

        return ResponseEntity.ok("Senha válida");
    }

    public ResponseEntity<String> validarCPF(@CPF String cpf) {
        if (cpf == null) {
            return ResponseEntity.badRequest().body("CPF inválido");
        }
        return ResponseEntity.ok("CPF válido");
    }

public ResponseEntity<String> validarDataNascimento(Date dataNascimento) {
        if (dataNascimento == null) {
            return ResponseEntity.badRequest().body("Data de nascimento não pode ser nula");
        }
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataNascimento);
        
        int ano = cal.get(Calendar.YEAR);

        if (!isAnoBissexto(ano)) {
            return ResponseEntity.badRequest().body("Ano de nascimento não é bissexto");
        }

        if (dataNascimento.after(new Date())) {
            return ResponseEntity.badRequest().body("Data de nascimento não pode estar no futuro");
        }

        Calendar calMaxima = Calendar.getInstance();
        calMaxima.add(Calendar.YEAR, -150); 

        if (cal.before(calMaxima)) {
            return ResponseEntity.badRequest().body("Idade inválida (superior a 150 anos)");
        }

        return ResponseEntity.ok("Data de nascimento válida");
    }

    private boolean isAnoBissexto(int ano) {
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }
}
       public ResponseEntity<String> validarEndereco(String endereco) {
        String url = "https://maps.googleapis.com/maps/api/place/findplacefromtext/json"
                + "?input=" + endereco
                + "&inputtype=textquery"
                + "&key=" + googlePlacesApiKey;

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
          
            if (Objects.requireNonNull(response.getBody()).contains("formatted_address")) {
                return ResponseEntity.ok("Endereço válido");
            } else {
                return ResponseEntity.badRequest().body("Endereço inválido");
            }
        } else {
            return ResponseEntity.badRequest().body("Falha ao validar endereço");
        }
    }
}


    public ResponseEntity<String> validarTelefone(String telefone) {
        String url = "https://lookups.twilio.com/v1/PhoneNumbers/" + telefone + "?Type=carrier&Type=caller-name&Type=caller-name";

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            return ResponseEntity.ok("Telefone válido");
        } else {
            return ResponseEntity.badRequest().body("Telefone inválido");
        }
    }

