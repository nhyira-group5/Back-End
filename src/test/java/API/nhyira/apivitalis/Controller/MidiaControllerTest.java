package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.Repository.MidiaRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Teste Mídia Controller")
class MidiaControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MidiaRepository midiaRepository;

    @Nested
    @DisplayName("Criação de mídias")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public class MidiaSaveTest {
        @Test
        @Transactional
        @DisplayName("Criação de mídia correta")
        void createMidia() throws Exception {
            var json = """
                    {
                      "nome": "NOMEXPTO",
                      "caminho": "CAMINHOXPTO",
                      "extensao": "EXTXPTO"
                    }
                    """;
            mockMvc.perform(MockMvcRequestBuilders.post("/midias/salvarMidia")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.nome").value("NOMEXPTO"))
                    .andExpect(jsonPath("$.caminho").value("CAMINHOXPTO"))
                    .andExpect(jsonPath("$.extensao").value("EXTXPTO"));
        }
    }

    @Nested
    @DisplayName("Busca de mídias")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public class MidiaShowTest {
        @Test
        @DirtiesContext
        @DisplayName("Busca por todas as mídia correta")
        void showMidia() throws Exception {
            int tamanho = midiaRepository.findAll().size();

            mockMvc.perform(get("/midias"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$").isArray())
                    .andExpect(jsonPath("$.length()").value(tamanho))
                    .andExpect(jsonPath("$[0].id").value(1))
                    .andExpect(jsonPath("$[0].nome").value("NOMEXPTO"))
                    .andExpect(jsonPath("$[0].caminho").value("CAMINHOXPTO"))
                    .andExpect(jsonPath("$[0].extensao").value("EXTXPTO"));
        }

        @Test
        @DirtiesContext
        @DisplayName("Busca por mídia correta")
        void showMidiaById() throws Exception {
            int id = 1;

            mockMvc.perform(get("/midias/" + id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.nome").value("NOMEXPTO"))
                    .andExpect(jsonPath("$.caminho").value("CAMINHOXPTO"))
                    .andExpect(jsonPath("$.extensao").value("EXTXPTO"));
        }
    }

    @Nested
    @DisplayName("Upload de mídias")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public class MidiaUploadTest {
        @Test
        @Transactional
        @DisplayName("Upload de imagem correta (JPG)")
        void uploadImage() throws Exception {
            // Passar uma imagem .JPG para o teste
            String imagemUrl = "";   // https://cdn.meutimao.com.br/_upload/idolos-do-corinthians/vampeta.jpg

            try (InputStream inputStream = new URL(imagemUrl).openStream()) {
                byte[] imagemBytes = FileCopyUtils.copyToByteArray(inputStream);

                MockMultipartFile imagemFile = new MockMultipartFile(
                        "file",
                        "imagem.jpg",
                        "imagem/jpg",
                        imagemBytes
                );

                MvcResult mvcResult = mockMvc.perform(multipart("/midias/uploadImage")
                                .file(imagemFile))
                        .andExpect(status().isOk())
                        .andReturn();

                MockHttpServletResponse response = mvcResult.getResponse();
                assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());
            }
        }

        @Test
        @Transactional
        @DisplayName("Upload de vídeo correto (MP4)")
        void uploadVideo() throws Exception {
            // Inserir um viedo .MP4 para passar
            String videoUrl = "";

            try (InputStream inputStream = new URL(videoUrl).openStream()) {
                byte[] videoBytes = FileCopyUtils.copyToByteArray(inputStream);

                MockMultipartFile videoFile = new MockMultipartFile(
                        "file",
                        "video.mp4",
                        "video/mp4",
                        videoBytes
                );

                MvcResult mvcResult = mockMvc.perform(multipart("/midias/uploadVideo")
                                .file(videoFile))
                        .andExpect(status().isOk())
                        .andReturn();

                MockHttpServletResponse response = mvcResult.getResponse();
                assertEquals(MockHttpServletResponse.SC_OK, response.getStatus());
            }
        }
    }

    @Nested
    @DisplayName("Edição de mídias")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public class MidiaUpdateTest {
        @Test
        @Transactional
        @DisplayName("Update de mídia correta")
        void updateMidia() throws Exception {
            var json = """
                    {
                      "nome": "NOMEXPTO",
                      "caminho": "CAMINHOXPTO",
                      "extensao": "EXTXPTO"
                    }
                    """;
            var id = 1;
            mockMvc.perform(MockMvcRequestBuilders.put("/midias/" + id)
                            .contentType("application/json")
                            .content(json))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(1))
                    .andExpect(jsonPath("$.nome").value("NOMEXPTO"))
                    .andExpect(jsonPath("$.caminho").value("CAMINHOXPTO"))
                    .andExpect(jsonPath("$.extensao").value("EXTXPTO"));
        }

        @Test
        @Transactional
        @DisplayName("Update de mídia incorreto, id inválido")
        void updateMidiaByIdInvalid() throws Exception {
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
        @DisplayName("Update de mídia incorreto, id não encontrado")
        void updateMidiaByIdNotFound() throws Exception {
            var id = 999;

            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/usuarios/" + id)
                            .contentType("application/json"))
                    .andExpect(status().isNotFound())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_NOT_FOUND);
        }
    }

    @Nested
    @DisplayName("Delete de mídias")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    public class MidiaDeleteTest {
        @Test
        @Transactional
        @DisplayName("Delete de mídia correta")
        void deleteUser() throws Exception {
            var id = 1;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/midias/" + id)
                            .contentType("application/json"))
                    .andExpect(status().isNoContent())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_NO_CONTENT);
        }

        @Test
        @Transactional
        @DisplayName("Delete de mídia incorreto, id inválido")
        void deleteUserByIdInvalid() throws Exception {
            var id = 0;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/midias/" + id)
                            .contentType("application/json"))
                    .andExpect(status().isBadRequest())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_BAD_REQUEST);
        }

        @Test
        @Transactional
        @DisplayName("Delete de mídia incorreto, id não encontrado")
        void deleteUserByIdNotFound() throws Exception {
            var id = 999;
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete("/midias/" + id)
                            .contentType("application/json"))
                    .andExpect(status().isNotFound())
                    .andReturn();

            MockHttpServletResponse responseBody = mvcResult.getResponse();
            assertEquals(responseBody.getStatus(), MockHttpServletResponse.SC_NOT_FOUND);
        }
    }
}