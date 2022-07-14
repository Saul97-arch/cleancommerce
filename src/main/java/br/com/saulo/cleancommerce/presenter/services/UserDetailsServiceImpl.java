package br.com.saulo.cleancommerce.presenter.services;

import br.com.saulo.cleancommerce.data.entities.CustomerData;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPACustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private JPACustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerData> customer = customerRepository.findByEmail(username);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new UsernameNotFoundException("Invalid data!");
    }
}
