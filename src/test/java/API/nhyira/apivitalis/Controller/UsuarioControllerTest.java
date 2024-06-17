package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Repository.UsuarioRepository;
import API.nhyira.apivitalis.Utils.ErrorMessageHelper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste Usuario Controller")
class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Nested
    @DisplayName("Criação de usuários")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public class UsuarioSaveTest {
        @Test
        @Transactional
        @DisplayName("Criação de usuário padrão correto")
        void createUserPadrao() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "1999-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388",
                          "academiaId" : 1
                        }
                    """;
            mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.cpf").value("71191770010"))
                    .andExpect(jsonPath("$.dtNasc").value("1999-08-08"))
                    .andExpect(jsonPath("$.email").value("silvaTest3rs@email.com"))
                    .andExpect(jsonPath("$.sexo").value("M"))
                    .andExpect(jsonPath("$.nome").value("Silva dos Santos"))
//                    .andExpect(jsonPath("$.senha").value("Daniel@23133")) -- SEM SENHA NA EXIBIÇÃO
                    .andExpect(jsonPath("$.tipo").value("USUARIO"))
                    .andExpect(jsonPath("$.nickname").value("T3STer@12388"));
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário personal correto")
        void createUserPersonal() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "1999-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "PERSONAL",
                          "nickname": "T3STer@12388",
                          "academiaId" : 1
                        }
                    """;
            mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.cpf").value("71191770010"))
                    .andExpect(jsonPath("$.dtNasc").value("1999-08-08"))
                    .andExpect(jsonPath("$.email").value("silvaTest3rs@email.com"))
                    .andExpect(jsonPath("$.sexo").value("M"))
                    .andExpect(jsonPath("$.nome").value("Silva dos Santos"))
//                    .andExpect(jsonPath("$.senha").value("Daniel@23133")) -- SEM SENHA NA EXIBIÇÃO
                    .andExpect(jsonPath("$.tipo").value("PERSONAL"))
                    .andExpect(jsonPath("$.nickname").value("T3STer@12388"));
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário incorreto, CPF já existente")
        void createCpfDuplicado() throws Exception {
            var json = """
                        {
                          "cpf": "47767654036",
                          "dtNasc": "1999-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388",
                          "academiaId" : 1
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isConflict())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_CONFLICT);
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário incorreto, nickname já existente")
        void createNicknameDuplicado() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "1999-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "pedR0G@",
                          "academiaId" : 1
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isConflict())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_CONFLICT);
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário incorreto, E-mail principal já existente")
        void createEmailPrincipalDuplicado() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "1999-08-08",
                          "email": "pedro@gmail.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388",
                          "academiaId" : 1
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isConflict())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_CONFLICT);
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário incorreto, nickname inválido")
        void createNicknameInvalido() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2001-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "t3er@"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário incorreto, sexo inválido")
        void createSexoInvalido() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2001-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "H",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário incorreto, e-mail inválido")
        void createEmailInvalido() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2001-08-08",
                          "email": "silvaTest3rs.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário incorreto, senha inválida")
        void createSenhaInvalida() throws Exception {
            var json = """
                        {
                          "cpf": "71191770010",
                          "dtNasc": "2001-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "123",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário incorreto, tipo inválido")
        void createTipoInvalido() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2001-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "AQUARIANO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário incorreto, cpf inválido")
        void createCpfInvalido() throws Exception {
            var json = """
                        {
                          "cpf" : "12345678901",
                          "dtNasc" : "2001-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Criação de usuário incorreto, data de nascimento NÃO é passado")
        void createDataNascimentoDifPassado() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2024-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/usuarios")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Nested
    @DisplayName("Exibição de usuários")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public class UsuarioShowTest {
        @Test
        @DirtiesContext
        @DisplayName("Busca de usuário correta")
        void ShowUserById() throws Exception {
            int id = 1;

            mockMvc.perform(get("/usuarios/" + id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.nickname").value("ylu1Gi@@"))
                    .andExpect(jsonPath("$.cpf").value("56438153036"))
                    .andExpect(jsonPath("$.nome").value("Luigi Vicchietti"))
                    .andExpect(jsonPath("$.dtNasc").value("2005-01-17"))
                    .andExpect(jsonPath("$.sexo").value("M"))
                    .andExpect(jsonPath("$.email").value("luigi@gmail.com"))
                    .andExpect(jsonPath("$.tipo").value("USUARIO"));
        }

        @Test
        @Transactional
        @DisplayName("Busca de usuário incorreto, id inválido")
        void ShowUserByIdInvalid() throws Exception {
            var id = 0;     // ID inválido

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/" + id)
                            .contentType("application/json"))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Busca de usuário incorreto, id inexistente")
        void ShowUserByIdNotExist() throws Exception {
            var id = 999;

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/" + id)
                            .contentType("application/json"))
                    .andExpect(status().isNotFound())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_NOT_FOUND);
        }

        @Test
        @Transactional
        @DisplayName("Busca de lista de usuário personais com dados")
        void showAllPersonal() throws Exception {
            int tamanho = usuarioRepository.buscarPersonal().size();

            mockMvc.perform(get("/usuarios/personais"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(tamanho))
                    .andExpect(jsonPath("$[0].id").value(2))
                    .andExpect(jsonPath("$[0].nickname").value("marC@SSilV4"))
                    .andExpect(jsonPath("$[0].cpf").value("92865867013"))
                    .andExpect(jsonPath("$[0].nome").value("Marcos Silva Oliveira Pinto Santos"))
                    .andExpect(jsonPath("$[0].dtNasc").value("1980-12-05"))
                    .andExpect(jsonPath("$[0].sexo").value("M"))
                    .andExpect(jsonPath("$[0].email").value("marcos@gmail.com"))
                    .andExpect(jsonPath("$[0].tipo").value("PERSONAL"));
        }

        @Test
        @Transactional
        @DisplayName("Busca de lista de usuários com dados")
        void showAllUsers() throws Exception {
            int tamanho = usuarioRepository.buscarUsuarios().size();

            mockMvc.perform(get("/usuarios"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(tamanho))
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].nickname").value("ylu1Gi@@"))
                    .andExpect(jsonPath("$[0].cpf").value("56438153036"))
                    .andExpect(jsonPath("$[0].nome").value("Luigi Vicchietti"))
                    .andExpect(jsonPath("$[0].dtNasc").value("2005-01-17"))
                    .andExpect(jsonPath("$[0].sexo").value("M"))
                    .andExpect(jsonPath("$[0].email").value("luigi@gmail.com"))
                    .andExpect(jsonPath("$[0].tipo").value("USUARIO"));
        }

        @Test
        @Transactional
        @DisplayName("Busca de usuário pelo seu nickname correto")
        void showUserByNickname() throws Exception {
            var json = """
                    {
                        "nickname": "ylu1Gi@@"
                    }
                    """;

            mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/por-username")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.nickname").value("ylu1Gi@@"))
                    .andExpect(jsonPath("$.cpf").value("56438153036"))
                    .andExpect(jsonPath("$.nome").value("Luigi Vicchietti"))
                    .andExpect(jsonPath("$.dtNasc").value("2005-01-17"))
                    .andExpect(jsonPath("$.sexo").value("M"))
                    .andExpect(jsonPath("$.email").value("luigi@gmail.com"))
                    .andExpect(jsonPath("$.tipo").value("USUARIO"));
        }

        @Test
        @Transactional
        @DisplayName("Busca de usuário pelo seu nickname incorreto, nickname não encontrado")
        void showUserByNicknameEmpty() throws Exception {
            var json = """
                    {
                        "nickname": "ylu1G@@"
                    }
                    """;

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/por-username")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isNotFound())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Test
    @DirtiesContext
    @DisplayName("Exportação de CSV")
    void exportToCsv() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/export/csv")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse responseBody = mvcResult.getResponse();
        assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_OK);
    }

    @Nested
    @DisplayName("Edição de usuários")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public class UserEditTest {
        @Test
        @Transactional
        @DisplayName("Edição de usuário correta")
        void updateUser() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2002-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388",
                          "academiaId": null
                        }
                    """;
            var id = 3;

            mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(3))
                    .andExpect(jsonPath("$.cpf").value("71191770010"))
                    .andExpect(jsonPath("$.dtNasc").value("2002-08-08"))
                    .andExpect(jsonPath("$.email").value("silvaTest3rs@email.com"))
                    .andExpect(jsonPath("$.sexo").value("M"))
                    .andExpect(jsonPath("$.nome").value("Silva dos Santos"))
                    .andExpect(jsonPath("$.tipo").value("USUARIO"))
                    .andExpect(jsonPath("$.nickname").value("T3STer@12388"));
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreta, id inválido")
        void ShowUserByIdInvalid() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2002-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            var id = 0;     // ID inválido

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreta, id inexistente")
        void ShowUserByIdNotExist() throws Exception {
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2002-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            var id = 999;

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isNotFound())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_NOT_FOUND);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreto, CPF já existente")
        void createCpfDuplicado() throws Exception {
            int id = 3;
            var json = """
                        {
                          "cpf": "47767654036",
                          "dtNasc": "1999-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isConflict())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_CONFLICT);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreto, nickname já existente")
        void createNicknameDuplicado() throws Exception {
            int id = 3;
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "1999-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "pedR0G@"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isConflict())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_CONFLICT);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreto, E-mail principal já existente")
        void createEmailPrincipalDuplicado() throws Exception {
            int id = 3;
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "1999-08-08",
                          "email": "pedro@gmail.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isConflict())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_CONFLICT);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreto, nickname inválido")
        void createNicknameInvalido() throws Exception {
            int id = 3;
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2001-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "t3er@"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreto, sexo inválido")
        void createSexoInvalido() throws Exception {
            int id = 3;
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2001-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "H",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreto, e-mail inválido")
        void createEmailInvalido() throws Exception {
            int id = 3;
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2001-08-08",
                          "email": "silvaTest3rs.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreto, senha inválida")
        void createSenhaInvalida() throws Exception {
            int id = 3;
            var json = """
                        {
                          "cpf": "71191770010",
                          "dtNasc": "2001-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "123",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreto, tipo inválido")
        void createTipoInvalido() throws Exception {
            int id = 3;
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2001-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "AQUARIANO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreto, cpf inválido")
        void createCpfInvalido() throws Exception {
            int id = 3;
            var json = """
                        {
                          "cpf" : "12345678901",
                          "dtNasc" : "2001-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Edição de usuário incorreto, data de nascimento NÃO é passado")
        void createDataNascimentoDifPassado() throws Exception {
            int id = 3;
            var json = """
                        {
                          "cpf" : "71191770010",
                          "dtNasc" : "2024-08-08",
                          "email": "silvaTest3rs@email.com",
                          "sexo": "M",
                          "nome": "Silva dos Santos",
                          "senha": "Daniel@23133",
                          "tipo": "USUARIO",
                          "nickname": "T3STer@12388"
                        }
                    """;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put("/usuarios/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Nested
    @DisplayName("Delete de usuários")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public class DeleteUsuarioTests {
        @Test
        @Transactional
        @DisplayName("Delete de usuário correto")
        void deleteUser() throws Exception {
            var id = 5;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/" + id)
                            .contentType("application/json"))
                    .andExpect(status().isNoContent())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_NO_CONTENT);
        }

        @Test
        @Transactional
        @DisplayName("Delete de usuário incorreto, id inválido")
        void deleteUserByIdInvalid() throws Exception {
            var id = 0;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/" + id)
                            .contentType("application/json"))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Delete de usuário incorreto, id não encontrado")
        void deleteUserByIdNotFound() throws Exception {
            var id = 999;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/usuarios/" + id)
                            .contentType("application/json"))
                    .andExpect(status().isNotFound())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_NOT_FOUND);
        }
    }
}