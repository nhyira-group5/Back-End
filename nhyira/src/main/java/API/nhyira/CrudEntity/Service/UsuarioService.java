package API.nhyira.CrudEntity.Service;

import API.nhyira.CrudEntity.DATABASE.UsuarioRepository;
import API.nhyira.CrudEntity.Model.UsuarioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.regex.Pattern;
import java.util.*;
import java.util.function.Predicate;

@Service
public class UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Boolean adicionarUsuario(UsuarioModel usuario) {
        if (usuario != null) {
            usuarioRepository.save(usuario);
            return true;
        }
        return false;
    }

    public UsuarioModel atualizarUsuario(int id, UsuarioModel usuarioDetails) {
        Optional<UsuarioModel> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            UsuarioModel usuario = usuarioOptional.get();

            try {
                usuario.setNome(usuarioDetails.getNome());
                usuario.setUsername(usuarioDetails.getUsername());
                usuario.setEmail(usuarioDetails.getEmail());
                usuario.setSenha(usuarioDetails.getSenha());
                usuario.setCpf(usuarioDetails.getCpf());
                usuario.setDtNasc(usuarioDetails.getDtNasc());
                usuario.setGenero(usuarioDetails.getGenero());
                usuario.setEmail2(usuarioDetails.getEmail2());
                usuario.setPeso(usuarioDetails.getPeso());
                usuario.setAltura(usuarioDetails.getAltura());

                adicionarUsuario(usuario);
                return usuario;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("Usuário não encontrado");
        }
    }

    public Boolean deletarUsuario(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        throw new NoSuchElementException("Usuário não encontrado");
    }

    public List<UsuarioModel> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> getUsuarioPorId(int id) {
        return usuarioRepository.findById(id);
    }

    public void validarUsuario(UsuarioModel usuario) throws Exception {
        Map<String, Predicate<String>> validacoes = criarValidacoes();

        for (Map.Entry<String, Predicate<String>> entry : validacoes.entrySet()) {
            String atributo = entry.getKey();
            Predicate<String> validacao = entry.getValue();
            String valor = getValue(usuario, atributo);

            if (!validacao.test(valor)) {
                throw new Exception("Erro de validação para: " + atributo);
            }
        }
    }

    private Map<String, Predicate<String>> criarValidacoes() {
        Map<String, Predicate<String>> validacoes = new HashMap<>();
        validacoes.put("nome", this::validarNome);
        validacoes.put("username", this::validarUsername);
        validacoes.put("cpf", this::validarCPF);
        validacoes.put("dtNasc", this::validarDtNasc);
        validacoes.put("genero", this::validarGenero);
        validacoes.put("email", this::validarEmail);
        validacoes.put("email2", this::validarEmail2);
        validacoes.put("senha", this::validarSenha);
        validacoes.put("peso", this::validarPeso);
        validacoes.put("altura", this::validarAltura);
        return validacoes;
    }

    private String getValue(UsuarioModel usuario, String atributo) {
        switch (atributo) {
            case "nome":
                return usuario.getNome();
            case "username":
                return usuario.getUsername();
            case "cpf":
                return usuario.getCpf();
            case "dtNasc":
                return usuario.getDtNasc() != null ? usuario.getDtNasc().toString() : null;
            case "genero":
                return usuario.getGenero();
            case "email":
                return usuario.getEmail();
            case "email2":
                return usuario.getEmail2();
            case "senha":
                return usuario.getSenha();
            case "peso":
                return Float.toString(usuario.getPeso());
            case "altura":
                return Float.toString(usuario.getAltura());
            default:
                return null;
        }
    }

    private boolean validarNome(String nome) {
        return nome != null && !nome.isEmpty();
    }

    private boolean validarUsername(String username) {
        return username != null && !username.isEmpty() && username.length() <= 20;
    }

    private boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    private boolean validarDtNasc(String dtNasc) {
        return dtNasc != null && !dtNasc.isEmpty();
    }

    private boolean validarGenero(String genero) {
        return genero != null && (genero.equals("M") || genero.equals("F") || genero.equals("N/A"));
    }

    public boolean validarEmail(String email) {
        return email != null && !email.isEmpty() && email.matches("^(.+)@(.+)$") && email.length() <= 100;
    }

    public boolean validarEmail2(String email2) {
        return email2 != null && !email2.isEmpty() && email2.matches("^(.+)@(.+)$") && email2.length() <= 100;
    }

    public boolean validarSenha(String senha) {
        if (senha == null || senha.isEmpty() || senha.length() < 8) {
            return false;
        }


        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
        return senha.matches(regex);
    }

    private static final String PATTERN_PESO = "^\\d{1,3}(\\.\\d{1,2})?$";
    private static final String PATTERN_ALTURA = "^\\d{1,3}(\\.\\d{1,2})?$";

    private boolean validarPeso(String peso) {
        if (peso == null || peso.isEmpty()) {
            return false;
        }
        return Pattern.matches(PATTERN_PESO, peso);
    }

    private boolean validarAltura(String altura) {
        if (altura == null || altura.isEmpty()) {
            return false;
        }
        return Pattern.matches(PATTERN_ALTURA, altura);
    }
}