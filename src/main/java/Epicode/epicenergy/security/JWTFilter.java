package Epicode.epicenergy.security;

import Epicode.epicenergy.entites.Utente;
import Epicode.epicenergy.exceptions.UnauthorizedException;
import Epicode.epicenergy.services.UtenteService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UtenteService utenteService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer "))
            throw new UnauthorizedException("Per favore inserisci correttamente il token nell'Authorization Header");

        String accessToken = authHeader.substring(7);

        jwtTools.verifyToken(accessToken);

        String id = jwtTools.extractIdFromToken(accessToken);
        Utente utenteAttuale = this.utenteService.findById(Integer.parseInt(id));

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(utenteAttuale, null, utenteAttuale.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);


        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return new AntPathMatcher().match("/auth/**", request.getServletPath());
    }
}
