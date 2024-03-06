import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PersonalControllerTest {

    @Test
    public void testObterPersonalPorId() {
        PersonalService personalService = mock(PersonalService.class);
        PersonalController personalController = new PersonalController();
        personalController.setPersonalService(personalService);

        Personal personal = new Personal();
        personal.setId(1L);
        when(personalService.obterPersonalPorId(1L)).thenReturn(new ResponseEntity<>(personal, HttpStatus.OK));

        ResponseEntity<Personal> response = personalController.obterPersonalPorId(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(personal, response.getBody());
    }

    @Test
    public void testListarPersonais() {
        PersonalService personalService = mock(PersonalService.class);
        PersonalController personalController = new PersonalController();
        personalController.setPersonalService(personalService);

        Page<Personal> personais = mock(Page.class);
        when(personais.getContent()).thenReturn(new ArrayList<>());
        when(personalService.listarPersonaisPaginados(any(Pageable.class))).thenReturn(personais);

        ResponseEntity<List<Personal>> response = personalController.listarPersonais(0, 10, "id", "asc");
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testCadastrarPersonal() {
        PersonalService personalService = mock(PersonalService.class);
        PersonalController personalController = new PersonalController();
        personalController.setPersonalService(personalService);

        Personal personal = new Personal();
        personal.setId(1L);
        when(personalService.cadastrarPersonal(personal)).thenReturn(new ResponseEntity<>("Personal cadastrado com sucesso", HttpStatus.CREATED));

        ResponseEntity<String> response = personalController.cadastrarPersonal(personal);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Personal cadastrado com sucesso", response.getBody());
    }

    @Test
    public void testAtualizarPersonal() {
        PersonalService personalService = mock(PersonalService.class);
        PersonalController personalController = new PersonalController();
        personalController.setPersonalService(personalService);

        Personal personal = new Personal();
        personal.setId(1L);
        when(personalService.atualizarPersonal(1L, personal)).thenReturn(new ResponseEntity<>("Personal atualizado com sucesso", HttpStatus.OK));

        ResponseEntity<String> response = personalController.atualizarPersonal(1L, personal);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Personal atualizado com sucesso", response.getBody());
    }

    @Test
    public void testDeletarPersonal() {
        PersonalService personalService = mock(PersonalService.class);
        PersonalController personalController = new PersonalController();
        personalController.setPersonalService(personalService);

        when(personalService.deletarPersonal(1L)).thenReturn(new ResponseEntity<>("Personal excluído com sucesso", HttpStatus.OK));

        ResponseEntity<String> response = personalController.deletarPersonal(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Personal excluído com sucesso", response.getBody());
    }
}
