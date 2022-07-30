package br.com.saulo.cleancommerce.core.usecases.security.authentication;

import br.com.saulo.cleancommerce.data.dto.AuthenticationRequest;
import br.com.saulo.cleancommerce.presenter.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationUseCase {

    @Autowired
    private AuthenticationService authenticationService;

    public AuthenticationUseCase() {
    }

    public ResponseEntity<?> authenticate(AuthenticationRequest authenticationRequest) {
        return authenticationService.auth(authenticationRequest);
    }
}
