package br.com.saulo.cleancommerce.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class OrderItem {
    Long id;
    Long quantity;
    Product product;
    Double total;

    public static OrderItem newInstance(Long quantity, Product product) {
        return new OrderItem(
                null,
                quantity,
                product,
                quantity * product.getPrice()
        );
    }
}
