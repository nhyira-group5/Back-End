package API.nhyira;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.function.Predicate;

@Service
public class PersonalService {

    private final List<PersonalModel> personals = new ArrayList<>();
    private long proximoId = 1;

    public List<PersonalModel> obterTodosPersonalsOrdenadosPorEspecialidade() {

        Map<String, List<PersonalModel>> personalsPorEspecialidade = new HashMap<>();


        for (PersonalModel personal : personals) {
            String especialidade = personal.getEspecialidade();
            if (!personalsPorEspecialidade.containsKey(especialidade)) {
                personalsPorEspecialidade.put(especialidade, new ArrayList<>());
            }
            personalsPorEspecialidade.get(especialidade).add(personal);
        }


        for (List<PersonalModel> lista : personalsPorEspecialidade.values()) {
            lista.sort(Comparator.comparing(PersonalModel::getNome));
        }


        List<String> especialidadesOrdenadas = new ArrayList<>(personalsPorEspecialidade.keySet());
        especialidadesOrdenadas.sort(Comparator.naturalOrder());


        List<PersonalModel> personalsOrdenados = new ArrayList<>();
        for (String especialidade : especialidadesOrdenadas) {
            personalsOrdenados.addAll(personalsPorEspecialidade.get(especialidade));
        }

        return personalsOrdenados;
    }


    public PersonalModel criarPersonal(PersonalModel personal) {
        try {
            validarPersonal(personal);
            personal.setId(proximoId++);
            personals.add(personal);
            return personal;
        } catch (Exception e) {
            throw new IllegalArgumentException("Erro ao criar personal: " + e.getMessage());
        }
    }

    public PersonalModel atualizarPersonal(Long id, PersonalModel detalhesPersonal) {
        PersonalModel personalExistente = obterPersonalPorId(id);
        if (personalExistente != null) {
            try {
                validarPersonal(detalhesPersonal);
                personalExistente.setNome(detalhesPersonal.getNome());
                personalExistente.setEmail(detalhesPersonal.getEmail());
                personalExistente.setSenha(detalhesPersonal.getSenha());
                personalExistente.setCpf(detalhesPersonal.getCpf());
                personalExistente.setTelefone(detalhesPersonal.getTelefone());
                personalExistente.setEndereco(detalhesPersonal.getEndereco());
                personalExistente.setEspecialidade(detalhesPersonal.getEspecialidade());
                return personalExistente;
            } catch (Exception e) {
                throw new IllegalArgumentException("Erro ao atualizar personal: " + e.getMessage());
            }
        } else {
            return null;
        }
    }

    public void excluirPersonal(Long id) {
        PersonalModel personal = obterPersonalPorId(id);
        if (personal != null) {
            personals.remove(personal);
        } else {
            throw new NoSuchElementException("Personal não encontrado");
        }
    }

    public List<PersonalModel> obterTodosPersonals() {
        return new ArrayList<>(personals);
    }

    public PersonalModel obterPersonalPorId(Long id) {
        for (PersonalModel personal : personals) {
            if (personal.getId().equals(id)) {
                return personal;
            }
        }
        return null;
    }


    public void validarPersonal(PersonalModel personal) throws Exception {
        Map<String, Predicate<String>> validacoes = criarValidacoes();

        for (Map.Entry<String, Predicate<String>> entry : validacoes.entrySet()) {
            String atributo = entry.getKey();
            Predicate<String> validacao = entry.getValue();
            String valor = obterValor(personal, atributo);

            if (!validacao.test(valor)) {
                throw new IllegalArgumentException("Erro de validação para: " + atributo);
            }
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
        validacoes.put("especialidade", this::validarEspecialidade);
        return validacoes;
    }

    private String obterValor(PersonalModel personal, String atributo) {
        switch (atributo) {
            case "nome":
                return personal.getNome();
            case "email":
                return personal.getEmail();
            case "senha":
                return personal.getSenha();
            case "cpf":
                return personal.getCpf();
            case "telefone":
                return personal.getTelefone();
            case "endereco":
                return personal.getEndereco();
            case "especialidade":
                return personal.getEspecialidade();
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

    private boolean validarEspecialidade(String especialidade) {
        List<String> especialidadesValidas = Arrays.asList("musculação", "crossfit", "yoga", "pilates");
        return especialidadesValidas.contains(especialidade.toLowerCase());
    }
}