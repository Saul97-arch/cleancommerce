package br.com.saulo.cleancommerce.data.entities.dto;

import lombok.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Value
public class AuthenticationRequest {
    String user;
    String password;
    public  UsernamePasswordAuthenticationToken convert() {
        return new UsernamePasswordAuthenticationToken(user, password);
    }
}
