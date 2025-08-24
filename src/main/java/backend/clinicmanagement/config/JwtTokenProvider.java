package backend.clinicmanagement.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String secret;
    private final long validityInMs;

    public JwtTokenProvider(@Value("${app.jwt.secret:change-me-to-longsecret-32b}") String secret,
                            @Value("${app.jwt.ttl-ms:3600000}") long validityInMs) {
        this.secret = secret;
        this.validityInMs = validityInMs;
    }

    public String createToken(String username, String role) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + validityInMs);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
