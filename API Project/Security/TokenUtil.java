import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public class TokenUtil {

    private static final String CHAVE_SECRETA = "Segredo";

    public static ResponseEntity<String> gerarToken(String username) {
        String token = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.HS512, CHAVE_SECRETA)
                .compact();
        return ResponseEntity.ok(token);
    }

    public static ResponseEntity<Boolean> validarToken(String token) {
        try {
            Jwts.parser().setSigningKey(CHAVE_SECRETA).parseClaimsJws(token);
            return ResponseEntity.ok(true);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }

    public static ResponseEntity<String> obterNomeUsuarioDoToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(CHAVE_SECRETA)
                    .parseClaimsJws(token)
                    .getBody();
            return ResponseEntity.ok(claims.getSubject());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido");
        }
    }
}
