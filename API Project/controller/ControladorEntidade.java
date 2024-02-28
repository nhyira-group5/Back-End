import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/entidades")
public class ControladorEntidade<T> {

    private List<T> entidades = new ArrayList<>();

    @Autowired
    private ServicoAPIBackend servicoAPIBackend;

    @PostMapping
    public ResponseEntity<T> criarEntidade(@RequestBody T entidade) {
        entidades.add(entidade);
        return ResponseEntity.status(HttpStatus.CREATED).body(entidade);
    }

    @GetMapping
    public ResponseEntity<Page<T>> obterTodasEntidades(@RequestParam(defaultValue = "0") int pagina,
                                                        @RequestParam(defaultValue = "10") int tamanho,
                                                        @RequestParam(defaultValue = "id") String ordenarPor,
                                                        @RequestParam(defaultValue = "asc") String ordenacao,
                                                        @RequestParam(required = false) String filtrarPor,
                                                        @RequestParam(required = false) String valorFiltro) {

        Sort.Direction direcao = ordenacao.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        PageRequest requisicaoPagina = PageRequest.of(pagina, tamanho, Sort.by(direcao, ordenarPor));

        Page<T> paginaEntidades;
        if (filtrarPor != null && valorFiltro != null) {
            paginaEntidades = servicoAPIBackend.obterEntidadesFiltradas(requisicaoPagina, filtrarPor, valorFiltro);
        } else {
            paginaEntidades = servicoAPIBackend.obterTodasEntidades(requisicaoPagina);
        }

        return ResponseEntity.ok(paginaEntidades);
    }

    @GetMapping("/{id}")
    public ResponseEntity<T> obterEntidadePorId(@PathVariable Long id) {
      
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> atualizarEntidade(@PathVariable Long id, @RequestBody T entidade) {
        
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEntidade(@PathVariable Long id) {
        
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
