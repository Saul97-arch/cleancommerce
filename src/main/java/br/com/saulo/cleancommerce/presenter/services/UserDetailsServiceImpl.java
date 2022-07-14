package br.com.saulo.cleancommerce.presenter.services;

import br.com.saulo.cleancommerce.data.entities.CustomerData;
import br.com.saulo.cleancommerce.data.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<CustomerData> customer = customerRepository.findByEmail(username);
        if (customer.isPresent()) {
            return customer.get();
        }
        throw new UsernameNotFoundException("Invalid data!");
    }
}
