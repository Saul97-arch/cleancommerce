package br.com.saulo.cleancommerce.core.domain;

import br.com.saulo.cleancommerce.data.entities.ProductData;
import br.com.saulo.cleancommerce.data.entities.exceptions.ProductNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@AllArgsConstructor
@Getter
@Setter
public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Product from(ProductData productData) {
        return new Product(
                productData.getId(),
                productData.getName(),
                productData.getDescription(),
                productData.getPrice()
        );
    }


}
