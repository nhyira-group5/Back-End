package API.nhyira.Service;

import API.nhyira.Model.PersonalModel;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import org.springframework.util.StringUtils;
import java.util.*;
import java.util.function.Predicate;

@Service
public class PersonalService {

    private final List<PersonalModel> personals = new ArrayList<>();
    private long proximoId = 1;

    public PersonalModel criarPersonal(PersonalModel personal) {
        try {
            validarPersonal(personal);
            personal.setIdPersonal((int) proximoId++);
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
                personalExistente.setUsername(detalhesPersonal.getUsername());
                personalExistente.setEmail(detalhesPersonal.getEmail());
                personalExistente.setEmail2(detalhesPersonal.getEmail2());
                personalExistente.setSenha(detalhesPersonal.getSenha());
                personalExistente.setCpf(detalhesPersonal.getCpf());
                personalExistente.setDtNasc(detalhesPersonal.getDtNasc());
                personalExistente.setGenero(detalhesPersonal.getGenero());
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
            if (personal.getIdPersonal() == id.intValue()) {
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
        validacoes.put("username", this::validarUsername);
        validacoes.put("email", this::validarEmail);
        validacoes.put("email2", this::validarEmail2);
        validacoes.put("senha", this::validarSenha);
        validacoes.put("cpf", this::validarCPF);
        validacoes.put("genero", this::validarGenero);
        validacoes.put("dtNasc", this::validarDtNasc);
        return validacoes;
    }

    private String obterValor(PersonalModel personal, String atributo) {
        switch (atributo) {
            case "nome":
                return personal.getNome();
            case "username":
                return personal.getUsername();
            case "email":
                return personal.getEmail();
            case "email2":
                return personal.getEmail2();
            case "senha":
                return personal.getSenha();
            case "cpf":
                return personal.getCpf();
            case "dtNasc":
                return String.valueOf(personal.getDtNasc());
            case "genero":
                return personal.getGenero();
            default:
                return null;
        }
    }


    private boolean validarNome(String nome) {
        return !StringUtils.isEmpty(nome);
    }

    public boolean validarUsername(String username) {
        return username != null && !username.isEmpty() && username.length() <= 20;
    }

    public boolean validarEmail(String email) {
        return email != null && !email.isEmpty() && email.matches("^(.+)@(.+)$") && email.length() <= 100;
    }

    public boolean validarEmail2(String email2) {
        return email2 == null || email2.isEmpty() || email2.matches("^(.+)@(.+)$") && email2.length() <= 100;
    }


    public boolean validarSenha(String senha) {
        if (senha == null || senha.isEmpty() || senha.length() < 6) {
            return false;
        }


        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$";
        return senha.matches(regex);
    }

    private boolean validarCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}");
    }

    public boolean validarGenero(String genero) {
        return genero != null && (genero.equals("M") || genero.equals("F") || genero.equals("N/A"));
    }

    private boolean validarDtNasc(String dtNascStr) {
        if (dtNascStr == null) {
            return false;
        }

        try {
            LocalDate dtNasc = LocalDate.parse(dtNascStr);

            LocalDate dataAtual = LocalDate.now();

            return dtNasc.isBefore(dataAtual);
        } catch (Exception e) {
            return false;
        }
    }
}
