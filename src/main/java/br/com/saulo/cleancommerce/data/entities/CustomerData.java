package br.com.saulo.cleancommerce.data.entities;

import br.com.saulo.cleancommerce.core.domain.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "customers")
@AllArgsConstructor
@Getter
public class CustomerData implements UserDetails {
    public CustomerData() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String CPF;
    String name;
    String address;
    String password;
    String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public static CustomerData fromCustomer(Customer customer) {
        return new CustomerData(
                customer.getId(),
                customer.getCPF(),
                customer.getName(),
                customer.getAddress(),
                customer.getPassword(),
                customer.getEmail()
        );
    }

    public Customer fromCustomerData() {
        return new Customer(
                id,
                CPF,
                name,
                address,
                password,
                email
        );
    }
}
