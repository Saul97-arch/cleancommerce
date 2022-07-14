package br.com.saulo.cleancommerce.presenter.controller.authentication;

import br.com.saulo.cleancommerce.core.usecases.security.authentication.AuthenticationUseCase;
import br.com.saulo.cleancommerce.data.entities.dto.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Component
public class AuthenticationController implements IAuthenticationController {

    @Autowired
    AuthenticationUseCase authenticationUseCase;

    @Override
    public ResponseEntity<?> auth(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        return authenticationUseCase.authenticate(authenticationRequest);
    }
}
