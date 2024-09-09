package API.nhyira.apivitalis.Pagamento.Controller;

import API.nhyira.apivitalis.Pagamento.DTO.PagamentoCreateEditDto;
import API.nhyira.apivitalis.Pagamento.Service.MercadoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private MercadoPagoService mercadoPagoService;

    @PostMapping("/criar")
    public ResponseEntity<Object> criarPagamento(@RequestBody PagamentoCreateEditDto dto) {
        try {
            Map<String, Object> paymentResponse = mercadoPagoService.criarPagamento(dto);
            return ResponseEntity.ok(paymentResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erro ao criar o pagamento: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPagamentoPorId(@PathVariable String id) {
        return mercadoPagoService.buscarPagamentoPorId(id);
    }
}