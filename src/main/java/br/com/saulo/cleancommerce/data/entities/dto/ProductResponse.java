package br.com.saulo.cleancommerce.data.entities.dto;

import br.com.saulo.cleancommerce.core.domain.Product;
import lombok.Value;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class ProductResponse {
    Long id;
    String name;
    String description;
    Double price;

    public static ProductResponse from(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public static List<ProductResponse> from(List<Product> productList) {
        return productList.stream().map(ProductResponse::from).collect(Collectors.toList());
    }
}
