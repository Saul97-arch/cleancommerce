package br.com.saulo.cleancommerce.presenter.services;

import br.com.saulo.cleancommerce.data.entities.CustomerData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import java.util.Date;

public class TokenService {
    @Value("${forum.jwt.expiration}")
    private String expiration;

    @Value("${forum.jwt.secret}")
    private String secret;

    public TokenService() {
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String generateToken(Authentication authentication) {
        CustomerData loggedUser = (CustomerData) authentication.getPrincipal();
        Date today = new Date();
        Date dataExpiracao = new Date(today.getTime() + Long.parseLong(expiration));

        return Jwts.builder().setIssuer("API do forum da Alura").setSubject(loggedUser.getId().toString())
                .setIssuedAt(today)
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }


    public Long getCustomerId(String token) {
        Claims body = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();


        return Long.parseLong(body.getSubject());
    }

}
