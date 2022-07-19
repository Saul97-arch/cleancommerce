package br.com.saulo.cleancommerce.core.domain;

import br.com.saulo.cleancommerce.data.entities.ProductData;
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

    public Product(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public ProductData toProductData() {
        return new ProductData(
                id,
                name,
                description,
                price
        );
    }
}
