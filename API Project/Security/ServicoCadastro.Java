import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServicoCadastro {

    @Autowired
    private ServicoValidacaoUsuario servicoValidacaoUsuario;

    @Autowired
    private ServicoValidacaoPersonal servicoValidacaoPersonal;

    @Autowired
    private UsuarioRepository usuarioRepository; 

    @Autowired
    private PersonalRepository personalRepository; 

    
    public ResponseEntity<String> cadastrarUsuario(Usuario usuario) {
        ResponseEntity<String> respostaNome = servicoValidacaoUsuario.validarNome(usuario.getNome());
        ResponseEntity<String> respostaEmail = servicoValidacaoUsuario.validarEmail(usuario.getEmail());
        ResponseEntity<String> respostaSenha = servicoValidacaoUsuario.validarSenha(usuario.getSenha());
        ResponseEntity<String> respostaCPF = servicoValidacaoUsuario.validarCPF(usuario.getCpf());
        ResponseEntity<String> respostaEndereco = servicoValidacaoUsuario.validarEndereco(usuario.getEndereco());
        ResponseEntity<String> respostaTelefone = servicoValidacaoUsuario.validarTelefone(usuario.getTelefone());

        if (respostaNome.getStatusCode().isError() || respostaEmail.getStatusCode().isError() ||
            respostaSenha.getStatusCode().isError() || respostaCPF.getStatusCode().isError() ||
            respostaEndereco.getStatusCode().isError() || respostaTelefone.getStatusCode().isError()) {
            return ResponseEntity.badRequest().body("Falha ao cadastrar usuário: verifique os dados fornecidos");
        }

       
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário cadastrado com sucesso");
    }

 
    public ResponseEntity<String> cadastrarPersonal(Personal personal) {
        ResponseEntity<String> respostaNome = servicoValidacaoPersonal.validarNome(personal.getNome());
        ResponseEntity<String> respostaEmail = servicoValidacaoPersonal.validarEmail(personal.getEmail());
        ResponseEntity<String> respostaSenha = servicoValidacaoPersonal.validarSenha(personal.getSenha());
        ResponseEntity<String> respostaCPF = servicoValidacaoPersonal.validarCPF(personal.getCpf());
        ResponseEntity<String> respostaEndereco = servicoValidacaoPersonal.validarEndereco(personal.getEndereco());
        ResponseEntity<String> respostaTelefone = servicoValidacaoPersonal.validarTelefone(personal.getTelefone());
        ResponseEntity<String> respostaEspecialidade = servicoValidacaoPersonal.validarEspecialidade(personal.getEspecialidade());

        if (respostaNome.getStatusCode().isError() || respostaEmail.getStatusCode().isError() ||
            respostaSenha.getStatusCode().isError() || respostaCPF.getStatusCode().isError() ||
            respostaEndereco.getStatusCode().isError() || respostaTelefone.getStatusCode().isError() ||
            respostaEspecialidade.getStatusCode().isError()) {
            return ResponseEntity.badRequest().body("Falha ao cadastrar personal: verifique os dados fornecidos");
        }

      
        personalRepository.save(personal);

        return ResponseEntity.ok("Personal cadastrado com sucesso");
    }
}
