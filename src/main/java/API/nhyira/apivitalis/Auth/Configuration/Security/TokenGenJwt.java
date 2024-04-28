package API.nhyira.apivitalis.Auth.Configuration.Security;

import API.nhyira.apivitalis.Entity.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class TokenGenJwt {

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;
    private long expiration_time;

    public String generateToken(final Authentication authentication, Usuario.TipoUsuario tipo) {
        final String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("tipo", tipo) // Passando o tipo como string diretamente
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME * 1_000))
                .compact();
    }


    public String getUsernameFromToken(String token) {
        return getExtractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


    public String extractTipoFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.get("tipo", String.class);
    }


    private boolean isTokenExpired(String token) {
        Date expiration = getExtractExpiration(token);
        return expiration.before(new Date(System.currentTimeMillis()));
    }

    private Date getExtractExpiration(String token) {
        return getExtractClaim(token, Claims::getExpiration);
    }

    private <T> T getExtractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(parseSecret())
                .build()
                .parseClaimsJws(token).getBody();
    }

    private SecretKey parseSecret() {
        return Keys.hmacShaKeyFor(this.SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

}
