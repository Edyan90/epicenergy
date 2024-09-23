package Epicode.epicenergy.security;

import Epicode.epicenergy.exceptions.UnauthorizedEx;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public class JWTTools {
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(Utente utente){
        return Jwts.builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()))
                .subject(String.valueOf(utente.getId()))
                .signWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .compact();
    }

    public void verifyToken(String token){
        try {
            Jwts.parser().verifyWith(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);
        }catch (Exception ex){
            throw new UnauthorizedEx("Problemi col token!");
        }
    }

    public String extractIDfromToken (String accessToken){
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secret.getBytes()))
                .build().parseSignedClaims(accessToken)
                .getPayload()
                .getSubject();
    }
}