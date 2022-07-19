package br.com.saulo.cleancommerce.config;

import br.com.saulo.cleancommerce.core.usecases.security.filters.JWTauthenticationFilter;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPACustomerRepository;
import br.com.saulo.cleancommerce.presenter.services.TokenService;
import br.com.saulo.cleancommerce.presenter.services.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.annotation.WebServlet;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
@Import({UserDetailsServiceImpl.class, TokenService.class})
public class WebSecurityConfig {


    protected final UserDetailsServiceImpl userDetailsService;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private JPACustomerRepository customerRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.GET, "/product/create").authenticated()
                .antMatchers(HttpMethod.GET, "/product/list").authenticated()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.GET, "/customer/**").permitAll()
                .antMatchers(HttpMethod.POST, "/customer/**").permitAll()
                .antMatchers(HttpMethod.POST, "/orders/**").permitAll()
                .antMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
                .anyRequest().permitAll()
                .and().headers().frameOptions().disable()
                .and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(new JWTauthenticationFilter(tokenService, customerRepository), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/**.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/configuration/**",
                        "/swagger-resources/**",
                        "h2-console/**");
    }
}
