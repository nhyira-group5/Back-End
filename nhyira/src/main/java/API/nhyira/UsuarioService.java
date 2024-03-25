package API.nhyira;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements UsuarioInterface {

    private final Map<String, List<UsuarioModel>> usuariosPorMeta = new HashMap<>();
    private final List<UsuarioModel> usuarios = new ArrayList<>();
    private long proximoId = 1;




    public void validarUsuario(UsuarioModel usuario) throws Exception {
        validarCamposUsuario(usuario);
        validarUsuarioRepetido(usuario);
        adicionarUsuario(usuario);
    }


    public List<UsuarioModel> listarUsuarios() {
        return usuarios;
    }


    public void adicionarUsuario(UsuarioModel usuario) {
        usuario.setId(proximoId++);
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
                // Verifica se os detalhes do usuário são diferentes dos detalhes existentes
                if (!usuario.equals(usuarioDetails)) {
                    usuario.setNome(usuarioDetails.getNome());
                    usuario.setEmail(usuarioDetails.getEmail());
                    usuario.setSenha(usuarioDetails.getSenha());
                    usuario.setCpf(usuarioDetails.getCpf());
                    usuario.setTelefone(usuarioDetails.getTelefone());
                    usuario.setEndereco(usuarioDetails.getEndereco());
                    usuario.setDataNascimento(usuarioDetails.getDataNascimento());
                    usuario.setFuncionario(usuarioDetails.isFuncionario());
                    usuario.setMeta(usuarioDetails.getMeta());
                    return usuario;
                } else {
                    throw new IllegalArgumentException("Nenhum campo do usuário foi modificado.");
                }
            } catch (Exception e) {
                throw new RuntimeException("Erro ao atualizar usuário: " + e.getMessage());
            }
        } else {
            throw new NoSuchElementException("Usuário não encontrado");
        }
    }


    public UsuarioModel buscarUsuarioPorId(int id) {
        for (UsuarioModel usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }


    public Map<String, List<UsuarioModel>> listarUsuariosPorMeta() {

        selectionSortOptimized(usuarios);

        Map<String, List<UsuarioModel>> usuariosPorMeta = new HashMap<>();

        for (UsuarioModel usuario : usuarios) {
            String meta = usuario.getMeta();
            usuariosPorMeta.computeIfAbsent(meta, key -> new ArrayList<>()).add(usuario);
        }

        return usuariosPorMeta;
    }

    private void selectionSortOptimized(List<UsuarioModel> usuarios) {
        int n = usuarios.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {

                int metaComparison = usuarios.get(j).getMeta().compareTo(usuarios.get(minIndex).getMeta());
                if (metaComparison < 0 || (metaComparison == 0 && usuarios.get(j).getNome().compareTo(usuarios.get(minIndex).getNome()) < 0)) {
                    minIndex = j;
                }
            }

            UsuarioModel temp = usuarios.get(minIndex);
            usuarios.set(minIndex, usuarios.get(i));
            usuarios.set(i, temp);
        }
    }

    private void validarCamposUsuario(UsuarioModel usuario) throws Exception {
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


    private void validarUsuarioRepetido(UsuarioModel usuario) throws IllegalArgumentException {
        for (UsuarioModel u : usuarios) {
            if (u.getCpf().equals(usuario.getCpf())) {
                throw new IllegalArgumentException("CPF duplicado");
            }
            if (u.getEmail().equals(usuario.getEmail())) {
                throw new IllegalArgumentException("E-mail duplicado");
            }
        }
    }

    public boolean excluirUsuario(int id) {
        Optional<UsuarioModel> usuarioOptional = usuarios.stream()
                .filter(usuario -> usuario.getId() == id)
                .findFirst();

        if (usuarioOptional.isPresent()) {
            UsuarioModel usuario = usuarioOptional.get();
            usuarios.remove(usuario);
            String meta = usuario.getMeta();
            usuariosPorMeta.get(meta).remove(usuario);
            return true;
        } else {
            return false;
        }
    }



    private Map<String, Predicate<String>> criarValidacoes() {
        Map<String, Predicate<String>> validacoes = new HashMap<>();
        validacoes.put("nome", this::validarNome);
        validacoes.put("email", this::validarEmail);
        validacoes.put("senha", this::validarSenha);
        validacoes.put("cpf", this::validarCPF);
        validacoes.put("telefone", this::validarTelefone);
        validacoes.put("endereco", this::validarEndereco);
        validacoes.put("dataNascimento", this::validarDataNascimento);
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
            case "dataNascimento":
                return usuario.getDataNascimento().toString();
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

    private  boolean validarSenha(String senha) {
        if (StringUtils.isEmpty(senha) || senha.length() < 6) {
            return false;
        }

        return senha.matches("^(?=.*[A-Z])(?=.*\\d).+$");
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

    private boolean validarDataNascimento(String dataNascimento) {
        try {
            LocalDate.parse(dataNascimento);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validarFuncionario(String funcionario) {
        // Nenhuma validação necessária, pois é um campo virtual
        return true;
    }

    private boolean validarMeta(String meta) {
        List<String> metasValidas = Arrays.asList("perder peso", "perder gordura", "ganhar massa muscular");
        return metasValidas.contains(meta.toLowerCase());
    }
}
