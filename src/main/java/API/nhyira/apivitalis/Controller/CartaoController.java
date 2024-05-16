package API.nhyira.apivitalis.Controller;

import API.nhyira.apivitalis.DTO.Cartao.CartaoCreateEditDto;
import API.nhyira.apivitalis.Entity.Cartao;
import API.nhyira.apivitalis.Service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    private final CartaoService cartaoService;

    @Autowired
    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @PostMapping
    public ResponseEntity<Cartao> createCartao(@RequestBody CartaoCreateEditDto dto) {
        Cartao cartao = cartaoService.createCartao(dto);
        return ResponseEntity.ok(cartao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cartao> getCartao(@PathVariable Integer id) {
        Optional<Cartao> cartao = cartaoService.getCartao(id);
        return cartao.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}