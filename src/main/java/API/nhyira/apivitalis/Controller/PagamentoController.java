package API.nhyira.apivitalis.Controller;


import API.nhyira.apivitalis.DTO.Pagamento.PagamentoCreateEditDto;
import API.nhyira.apivitalis.Service.PayPalService;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private PayPalService servico;

    @PostMapping("/pagar")
    public ResponseEntity<?> pagamento(@RequestBody PagamentoCreateEditDto dto) {
        try {
            Payment pagamentoCriado = servico.createPayment(dto, "USD", "paypal",
                    "sale", "pagamento teste",
                    "https://www.sandbox.paypal.com", "https://api-m.sandbox.paypal.com/v3/vault/payment-token");
            List<Links> links = pagamentoCriado.getLinks();
            for (Links link : links) {
                if (link.getRel().equals("approval_url")) {
                    URI uri = ServletUriComponentsBuilder.fromUriString(link.getHref()).build().toUri();
                    return ResponseEntity.status(HttpStatus.FOUND).location(uri).build();
                }
            }
            return ResponseEntity.ok(Collections.singletonMap("resposta", pagamentoCriado));
        } catch (PayPalRESTException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("resposta", e.getMessage()));
        }
    }

    @GetMapping("/completar/pagamento")
    public Map<String, Object> completarPagamento(@RequestParam("paymentId") String idPagamento, @RequestParam("PayerID") String idPagador) {
        try {
            Payment pagamento = servico.executePayment(idPagamento, idPagador);
            if (pagamento.getState().equals("approved")) {
                return Collections.singletonMap("resposta", "pagamento aprovado");
            }
        } catch (PayPalRESTException e) {
            System.out.println(e.getMessage());
        }
        return Collections.singletonMap("resposta", "erro na aprovação do pagamento");
    }
}
