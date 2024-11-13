package API.nhyira.apivitalis.Service;

import API.nhyira.apivitalis.Auth.Configuration.Security.TokenGenJwt;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioLoginDto;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioTokenDto;
import API.nhyira.apivitalis.DTO.Midia.MidiaCreateEditDto;
import API.nhyira.apivitalis.DTO.Midia.MidiaExibitionDto;
import API.nhyira.apivitalis.DTO.Midia.MidiaMapper;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioUpdateMidia;
import API.nhyira.apivitalis.EmailSender.Service.EmailService;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioCreateEditDto;
import API.nhyira.apivitalis.EmailSender.Model.EmailModel;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioExibitionDto;
import API.nhyira.apivitalis.DTO.Usuario.UsuarioMapper;
import API.nhyira.apivitalis.Entity.*;
import API.nhyira.apivitalis.Exception.ConflitoException;
import API.nhyira.apivitalis.Exception.ErroClienteException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import API.nhyira.apivitalis.Exception.SemConteudoException;
import API.nhyira.apivitalis.Pagamento.Service.PagamentoService;
import API.nhyira.apivitalis.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository uRep;
    private final RotinaUsuarioRepository ruRep;
    private final MetaRepository mRep;
    private final FichaService fichaService;
    private final EnderecoService enderecoService;
    private final PasswordEncoder encoder;
    private final EmailService emailService;
    private final AuthenticationManager authenticationManagerForUsuarios;
    private final TokenGenJwt tokenGenJwt;
    private final EspecialidadePorPersonalRepository especialidadePorPersonal;
    private final MidiaService midiaService;
    private final PagamentoService pagSrv;
    private final Set<String> emailsEnviados = new HashSet<>();

    public UsuarioTokenDto autenticar(UsuarioLoginDto usuarioLogin) {
        final UsernamePasswordAuthenticationToken credentials = new UsernamePasswordAuthenticationToken(
                usuarioLogin.getLogin(), usuarioLogin.getSenha()
        );

        final Authentication auth = this.authenticationManagerForUsuarios.authenticate(credentials);

        Optional<Usuario> usuarioByEmail = uRep.findByEmailIgnoreCase(usuarioLogin.getLogin());
        Optional<Usuario> usuarioByUsername = uRep.findByNickname(usuarioLogin.getLogin());

        Usuario.TipoUsuario tipo = null;
        Usuario user = null;

        if (usuarioByEmail.isPresent()) {
            tipo = usuarioByEmail.get().getTipo();
            user = usuarioByEmail.get();
        } else if (usuarioByUsername.isPresent()) {
            tipo = usuarioByUsername.get().getTipo();
            user = usuarioByUsername.get();
        } else {
            throw new ResponseStatusException(404, "Credencial de login do usuário não cadastrado!", null);
        }

        final String token = tokenGenJwt.generateToken(auth, tipo);
        String emailDoUsuario = user.getEmail();

//        if (!emailsEnviados.contains(emailDoUsuario)) {
//            enviarEmailDeBoasVindas(emailDoUsuario);
//            emailsEnviados.add(emailDoUsuario);
//        }

        SecurityContextHolder.getContext().setAuthentication(auth);
        return UsuarioMapper.of(user, token);
    }

    private void enviarEmailDeBoasVindas(String destinatario) {
        String assunto = "Bem-vindo à Aplicação";
        String conteudo = gerarConteudoEmailDeBoasVindas(destinatario);
        emailService.enviarEmail(new EmailModel(destinatario, assunto, conteudo));
    }

    private String gerarConteudoEmailDeBoasVindas(String destinatario) {
        return "Olá " + destinatario + ",\n\n" +
                "Obrigado por se cadastrar na aplicação! Aproveite para explorar nossos recursos e funcionalidades.\n\n" +
                "Atenciosamente,\nSua Equipe de Suporte Daniel Santos";
    }

    public Usuario createUser(Usuario usuario, Integer enderecoId) {
        if (usuario == null) {
            throw new ErroClienteException("Usuario");
        }
        if(cpfUnique(usuario.getCpf())){
            throw new ConflitoException("CPF");
        }
        if (nomeUnique(usuario.getNickname())){
            throw new ConflitoException("Nickname");
        }
        if (emailUnique(usuario.getEmail())){
            throw new ConflitoException("Email");
        }
        if (enderecoId != null){
            Endereco endereco = enderecoService.showEndereco(enderecoId);
            usuario.setEnderecoId(endereco);
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        uRep.save(usuario);
        return usuario;
    }

    public List<Usuario> showAllUsers() {
        List<Usuario> allUsers = uRep.buscarUsuarios();
        if (allUsers.isEmpty()){
            throw new SemConteudoException("Usuarios");
        }

        return allUsers;
    }

    public List<Usuario> showAllUsersPersonal() {
        List<Usuario> trainers = uRep.buscarPersonal();
        if (trainers.isEmpty()) throw new SemConteudoException("Personais");
        return trainers;
    }

    public List<Usuario> showTrainersByMeta(int idMeta) {
        List<Usuario> trainers = uRep.searchTrainersByMeta(idMeta);
        if (trainers.isEmpty()) throw new SemConteudoException("Personais por meta");
        return trainers;
    }

    public Usuario showUserById(int id) {
        Usuario usuario = uRep.findById(id).orElseThrow(() -> new NaoEncontradoException("usuario"));
        return usuario;
    }

    public List<Usuario> showUsserAfiliado(int id){
        Usuario usuario = showUserById(id);
        List<Usuario> usuarios = uRep.findByPersonalIdIs(usuario);
        return usuarios;
    }

    public UsuarioExibitionDto buscarPersonalPorUsuario(Integer id) {
        Usuario usuarioEncontrado = uRep.findById(id).orElseThrow(() -> new NaoEncontradoException("Usuario"));
        System.out.println(usuarioEncontrado);
        return UsuarioMapper.toExibition(usuarioEncontrado.getPersonalId());
    }

    public Meta searchMetaUsuario (Usuario usuario) {
        RotinaUsuario ru = ruRep.findByUsuarioIdIs(usuario).orElse(null);
        if (ru == null) return null;
        Meta m = mRep.findById(ru.getMetaId().getIdMeta()).orElseThrow(() -> new NaoEncontradoException("Meta"));
        return m;
    }

    public List<EspecialidadePorPersonal> buscarEspecialidade(Usuario usuario){
        List<EspecialidadePorPersonal> espe = especialidadePorPersonal.findByPersonalIdIs(usuario);
        return espe;
    }

    public Usuario updtUser(int id, UsuarioCreateEditDto updatedUser) {
        if (uRep.existsById(id)) {
            Optional<Usuario> user = uRep.findById(id);
            user.orElseThrow(() -> new NaoEncontradoException("usuario"));
            if(cpfUnique(updatedUser.getCpf())){
                throw new ConflitoException("CPF");
            }
            if (nomeUnique(updatedUser.getNickname())){
                throw new ConflitoException("UserName");
            }
            if (emailUnique(updatedUser.getEmail())){
                throw new ConflitoException("Email");
            }
            updatedUser.setSenha(encoder.encode(updatedUser.getSenha()));
            Usuario usuario = UsuarioMapper.toEditDto(user.get(), updatedUser);
            uRep.save(usuario);
            return usuario;
        }
        throw new NaoEncontradoException("Id");
    }

    public Usuario afiliacao(Usuario usuario, Usuario personal){
        usuario.setPersonalId(personal);
        uRep.save(usuario);
        return usuario;
    }

    public boolean delUser(int id) {
        if (!uRep.existsById(id)) {
            throw new NaoEncontradoException("Id");
        }
        uRep.deleteById(id);
        return true;

    }

    public Midia AtualizarFoto(int idUsuario, UsuarioUpdateMidia midiaUpdate){
        Usuario usuario = showUserById(idUsuario);
        Midia midia = midiaService.findById(midiaUpdate.getIdMidia());
        usuario.setMidiaId(midia);
        uRep.save(usuario);
        return midia;
    }

    public List<Usuario> getAllUsers(){return uRep.findAll();}

    public boolean nomeUnique(String username) {
        return uRep.findByNickname(username).isPresent();
    }

    public boolean emailUnique(String email) {
        return uRep.findByEmailIgnoreCase(email).isPresent();
    }

    public boolean cpfUnique(String cpf) {
        return uRep.findByCpf(cpf).isPresent();
    }

    public UsuarioExibitionDto findUserByUsername(String username) {
        try {
            List<Usuario> allUsers = uRep.buscarUsuarios();
            Collections.sort(allUsers, Comparator.comparing(Usuario::getNickname));
            int index = binarySearch(allUsers, username);
            if (index != -1) {
                Usuario user = allUsers.get(index);
                Meta meta = searchMetaUsuario(user);
                boolean pagamentoAtivo = pagSrv.verifyUserPagamento(user);
                return UsuarioMapper.toExibition(user, meta, pagamentoAtivo);
            } else {
                return null;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Erro ao buscar o usuário: " + e.getMessage());
        }
    }

    private int binarySearch(List<Usuario> usuarios, String usernameAlvo) {
        int esquerda = 0;
        int direita = usuarios.size() - 1;

        while (esquerda <= direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            String usernameAtual = usuarios.get(meio).getNickname();

            int comparacao = usernameAtual.compareTo(usernameAlvo);

            if (comparacao == 0) {
                return meio;
            } else if (comparacao < 0) {
                esquerda = meio + 1;
            } else {
                direita = meio - 1;
            }
        }
        return -1;
    }
}
