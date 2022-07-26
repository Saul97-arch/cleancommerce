package br.com.saulo.cleancommerce.presenter.controller.authentication;

import br.com.saulo.cleancommerce.data.dto.AuthenticationRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public interface IAuthenticationController {

    @PostMapping
    public ResponseEntity<?> auth(AuthenticationRequest authenticationRequest);
}
