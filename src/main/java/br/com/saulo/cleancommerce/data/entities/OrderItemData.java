package br.com.saulo.cleancommerce.data.entities;

import br.com.saulo.cleancommerce.core.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@AllArgsConstructor
@Getter
@Setter
@Table(name = "order_items")
public class OrderItemData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long quantity;
    private Double total;
    @ManyToOne
    OrderData orderData;

    public OrderItemData() {
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name = "product_id")
    private ProductData productData;

    public static List<OrderItemData> from(List<OrderItem> orderItems) {
        List<OrderItemData> orderItemHashSet = new ArrayList<>();
        orderItems.forEach(orderItem -> {
            var orderItemData = OrderItemData.from(orderItem);
            orderItemHashSet.add(orderItemData);
        });

        return orderItemHashSet;
    }

    public static OrderItemData from(OrderItem orderItem) {
        return new OrderItemData(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getTotal(),
                null,
                null
        );
    }
}
