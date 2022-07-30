package br.com.saulo.cleancommerce.data.entities;

import br.com.saulo.cleancommerce.core.domain.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private Double unitPrice;

    @ManyToOne
    @JoinColumn(name = "order_id")
    OrderData orderData;

    public OrderItemData() {}

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductData productData;

    public static List<OrderItemData> from(List<OrderItem> orderItems) {
        List<OrderItemData> orderItemsList = new ArrayList<>();
        orderItems.forEach(orderItem -> {
            var orderItemData = OrderItemData.from(orderItem);
            orderItemsList.add(orderItemData);
        });

        return orderItemsList;
    }

    public static OrderItemData from(OrderItem orderItem) {
        return new OrderItemData(
                orderItem.getId(),
                orderItem.getQuantity(),
                orderItem.getUnitPrice(),
                null,
                null
        );
    }

    public static OrderItemData newInstance(
            Long quantity,
            Double unitPrice,
            OrderData orderData,
            ProductData productData) {
        return new OrderItemData(
                null,
                quantity,
                unitPrice,
                orderData,
                productData
        );
    }
}
