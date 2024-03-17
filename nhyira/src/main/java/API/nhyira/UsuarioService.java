package API.nhyira;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UsuarioInterface {

    private final Map<String, List<UsuarioModel>> usuariosPorMeta = new HashMap<>();
    private final List<UsuarioModel> usuarios = new ArrayList<>();

    @Override
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
        adicionarUsuario(usuario);
    }

    public void adicionarUsuario(UsuarioModel usuario) {
        String meta = usuario.getMeta();
        usuariosPorMeta.computeIfAbsent(meta, key -> new ArrayList<>()).add(usuario);
        usuarios.add(usuario);
    }

    public UsuarioModel atualizarUsuario(int id, UsuarioModel usuarioDetails) {
        List<UsuarioModel> usuarios = usuariosPorMeta.values().stream()
                .flatMap(List::stream)
                .filter(usuario -> usuario.getId() == id)
                .collect(Collectors.toList());

        if (!usuarios.isEmpty()) {
            UsuarioModel usuario = usuarios.get(0);
            try {
                validarUsuario(usuarioDetails);
                usuario.setNome(usuarioDetails.getNome());
                usuario.setEmail(usuarioDetails.getEmail());
                usuario.setSenha(usuarioDetails.getSenha());
                usuario.setCpf(usuarioDetails.getCpf());
                usuario.setTelefone(usuarioDetails.getTelefone());
                usuario.setEndereco(usuarioDetails.getEndereco());
                usuario.setIdade(usuarioDetails.getIdade());
                usuario.setFuncionario(usuarioDetails.isFuncionario());
                usuario.setMeta(usuarioDetails.getMeta());
                return usuario;
            } catch (Exception e) {
                throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("Usuário não encontrado");
        }
    }

    public Map<String, List<UsuarioModel>> listarUsuariosPorMeta() {

        usuarios.sort(Comparator.comparing(UsuarioModel::getMeta).thenComparing(UsuarioModel::getNome));

        Map<String, List<UsuarioModel>> usuariosPorMeta = new HashMap<>();

        for (UsuarioModel usuario : usuarios) {
            String meta = usuario.getMeta();
            usuariosPorMeta.computeIfAbsent(meta, key -> new ArrayList<>()).add(usuario);
        }

        return usuariosPorMeta;
    }

    private Map<String, Predicate<String>> criarValidacoes() {
        Map<String, Predicate<String>> validacoes = new HashMap<>();
        validacoes.put("nome", this::validarNome);
        validacoes.put("email", this::validarEmail);
        validacoes.put("senha", this::validarSenha);
        validacoes.put("cpf", this::validarCPF);
        validacoes.put("telefone", this::validarTelefone);
        validacoes.put("endereco", this::validarEndereco);
        validacoes.put("idade", this::validarIdade);
        validacoes.put("funcionario", this::validarFuncionario);
        validacoes.put("meta", this::validarMeta);
        return validacoes;
    }

    private String getValue(UsuarioModel usuario, String atributo) {
        switch (atributo) {
            case "nome":
                return usuario.getNome();
            case "email":
                return usuario.getEmail();
            case "senha":
                return usuario.getSenha();
            case "cpf":
                return usuario.getCpf();
            case "telefone":
                return usuario.getTelefone();
            case "endereco":
                return usuario.getEndereco();
            case "idade":
                return Integer.toString(usuario.getIdade());
            case "funcionario":
                return Boolean.toString(usuario.isFuncionario());
            case "meta":
                return usuario.getMeta();
            default:
                return null;
        }
    }

    private boolean validarNome(String nome) {
        return !StringUtils.isEmpty(nome);
    }

    private boolean validarEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            return false;
        }
        String regex = "^(.+)@(.+)$";
        return email.matches(regex);
    }

    private boolean validarSenha(String senha) {
        return !StringUtils.isEmpty(senha) && senha.length() >= 6;
    }

    private boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    private boolean validarTelefone(String telefone) {
        return telefone != null && telefone.matches("\\(\\d{2}\\) \\d{5}-\\d{4}");
    }


    private boolean validarEndereco(String endereco) {
        return !StringUtils.isEmpty(endereco);
    }

    private boolean validarIdade(String idade) {
        try {
            int idadeInt = Integer.parseInt(idade);
            return idadeInt >= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean validarFuncionario(String funcionario) {
        return "true".equalsIgnoreCase(funcionario) || "false".equalsIgnoreCase(funcionario);
    }


    private boolean validarMeta(String meta) {
        List<String> metasValidas = Arrays.asList("perder peso", "perder gordura", "ganhar massa muscular");
        return metasValidas.contains(meta.toLowerCase());
    }
}
