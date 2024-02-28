import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public class ServicoCadastro {

    @Autowired
    private ServicoValidacao servicoValidacao;

    @Autowired
    private EmailService emailService;

    public ResponseEntity<String> cadastrarUsuario(Usuario usuario) {

        
        ResponseEntity<String> validacaoEmail = servicoValidacao.validarEmail(usuario.getEmail());
        if (validacaoEmail.getStatusCode() != HttpStatus.OK) {
            return validacaoEmail;
        }

        ResponseEntity<String> validacaoSenha = servicoValidacao.validarSenha(usuario.getSenha());
        if (validacaoSenha.getStatusCode() != HttpStatus.OK) {
            return validacaoSenha;
        }

        ResponseEntity<String> validacaoCPF = servicoValidacao.validarCPF(usuario.getCpf());
        if (validacaoCPF.getStatusCode() != HttpStatus.OK) {
            return validacaoCPF;
        }

        if (usuario.getTelefone() == null || usuario.getTelefone().isEmpty()) {
            return ResponseEntity.badRequest().body("Telefone não pode estar vazio");
        }

        if (usuario.getEndereco() == null || usuario.getEndereco().isEmpty()) {
            return ResponseEntity.badRequest().body("Endereço não pode estar vazio");
        }

        ResponseEntity<String> tokenResponse = TokenUtil.gerarToken(usuario.getEmail());
        if (tokenResponse.getStatusCode() != HttpStatus.OK) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao gerar token");
        }
        String token = tokenResponse.getBody();

        try {
            String assunto = "Confirmação de Cadastro";
            String corpo = "Olá " + usuario.getNome() + ",<br><br>" +
                    "Obrigado por se cadastrar em nosso sistema! Por favor, clique no link abaixo para confirmar seu e-mail:<br>" +
                    "<a href=\"https://seusite.com/confirmar?token=" + token + "\">Confirmar E-mail</a>";
            emailService.enviarEmail(usuario.getEmail(), assunto, corpo);
        } catch (MessagingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao enviar e-mail de confirmação");
        }

        return ResponseEntity.ok("Usuário cadastrado com sucesso! Um e-mail de confirmação foi enviado para " + usuario.getEmail());
    }
}
