package br.com.saulo.cleancommerce.data.entities;

import br.com.saulo.cleancommerce.core.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "products")
public class ProductData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;
    private Double price;

    public ProductData(String name, String description, Double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public static Product fromProductData(ProductData productData) {
        return new Product(
                productData.name,
                productData.description,
                productData.price
        );
    }

    public static ProductData from(Product product) {
        return new ProductData(
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }
}
