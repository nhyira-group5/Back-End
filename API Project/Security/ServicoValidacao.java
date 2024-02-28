import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServicoValidacao {

    @Autowired
    private EmailService emailService;

    public ResponseEntity<String> validarEmail(String email) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[A-Za-z]{2,}$")) {
            return ResponseEntity.badRequest().body("Email inválido");
        }
        
        
        try {
            String mensagem = "Olá! Por favor, confirme seu e-mail clicando neste link: [link de confirmação]";
            emailService.enviarEmail(email, "Confirmação de E-mail", mensagem);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar e-mail de confirmação");
        }
        
        return ResponseEntity.ok("Um e-mail de confirmação foi enviado para " + email);
    }

    public ResponseEntity<String> validarSenha(String senha) {
        if (senha == null || senha.length() < 8) {
            return ResponseEntity.badRequest().body("A senha deve ter pelo menos 8 caracteres");
        }
        
       
        if (!senha.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            return ResponseEntity.badRequest().body("A senha deve conter pelo menos 8 caracteres, incluindo pelo menos uma letra maiúscula, uma letra minúscula, um número e um caractere especial");
        }
        
        return ResponseEntity.ok("Senha válida");
    }

    public ResponseEntity<String> validarCPF(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            return ResponseEntity.badRequest().body("CPF inválido");
        }
        return ResponseEntity.ok("CPF válido");
    }

    public ResponseEntity<String> verificarMaioridade(int idade) {
        if (idade < 18) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("O usuário deve ser maior de idade");
        }
        return ResponseEntity.ok("Usuário é maior de idade");
    }
    
    public ResponseEntity<String> verificarFuncionarioEstabelecimento(boolean isFuncionario) {
        if (!isFuncionario) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("O usuário não é um funcionário do estabelecimento");
        }
        return ResponseEntity.ok("Usuário é um funcionário do estabelecimento");
    }
}
