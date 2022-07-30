package br.com.saulo.cleancommerce.core.usecases.security.filters;

import br.com.saulo.cleancommerce.data.entities.CustomerData;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPACustomerRepository;
import br.com.saulo.cleancommerce.presenter.services.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class JWTauthenticationFilter extends OncePerRequestFilter {


    private final TokenService tokenService;
    private final JPACustomerRepository customerRepository;
    public JWTauthenticationFilter(TokenService tokenService, JPACustomerRepository customerRepository) {
        this.tokenService = tokenService;
        this.customerRepository = customerRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = retrieveToken(request);
        boolean valid = tokenService.isTokenValid(token);
        if (valid) {
            authenticateToken(token);
        }
        filterChain.doFilter(request, response);
    }

    private void authenticateToken(String token) {
        Long customerId = tokenService.getCustomerId(token);
        // pode ser CustomerData, verificar isso!
        Optional<CustomerData> customer = customerRepository.findById(customerId);
        assert customer.isPresent();
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(customer, null, customer.get().getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    private String retrieveToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7);
    }
}
