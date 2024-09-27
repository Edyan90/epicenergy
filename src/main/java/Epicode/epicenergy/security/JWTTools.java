package Epicode.epicenergy.security;

<<<<<<< Updated upstream
import Epicode.epicenergy.entities.Utente;
import Epicode.epicenergy.exceptions.UnauthorizedEx;
=======
import Epicode.epicenergy.entites.Utente;
import Epicode.epicenergy.exceptions.UnauthorizedException;
>>>>>>> Stashed changes
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTTools {
<<<<<<< Updated upstream
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Utente utente) {
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()))
=======

    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Utente utente) { // dato un utente generami un token per esso
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7)) //
>>>>>>> Stashed changes
                .subject(String.valueOf(utente.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String token) {
<<<<<<< Updated upstream
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            throw new UnauthorizedEx("Problemi col token!");
        }
    }

    public String extractIDfromToken(String accessToken) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(accessToken)
                .getPayload()
                .getSubject();
=======

        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new UnauthorizedException("Problemi col token! Per favore effettua di nuovo il login!");
        }
    }

    public String extractIdFromToken(String accessToken) {
        return Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parseSignedClaims(accessToken).getPayload().getSubject(); // Il subject è l'id dell'utente
>>>>>>> Stashed changes
    }
}
