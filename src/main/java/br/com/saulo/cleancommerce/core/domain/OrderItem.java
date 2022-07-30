package br.com.saulo.cleancommerce.core.domain;

import br.com.saulo.cleancommerce.data.entities.OrderItemData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class OrderItem {
    Long id;
    Long quantity;
    Product product;
    Double unitPrice;

    public static OrderItem newInstance(Long quantity, Product product) {
        return new OrderItem(
                product.getId(),
                quantity,
                product,
                quantity * product.getPrice()
        );
    }

    public static List<OrderItem> from(List<OrderItemData> orderItems) {
        List<OrderItem> list = new ArrayList<>();
        for (OrderItemData item : orderItems) {
            OrderItem orderItem = OrderItem.newInstance(item.getQuantity(), Product.from(item.getProductData()));
            list.add(orderItem);
        }
        return list;
    }
}
