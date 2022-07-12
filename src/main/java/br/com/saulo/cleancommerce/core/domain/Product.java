package br.com.saulo.cleancommerce.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
}
