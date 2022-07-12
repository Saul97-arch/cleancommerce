package br.com.saulo.cleancommerce.data.entities;

import lombok.AllArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "customers")
@AllArgsConstructor
public class CustomerData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String CPF;
    String name;
    String address;
    String password;
    String email;
}
