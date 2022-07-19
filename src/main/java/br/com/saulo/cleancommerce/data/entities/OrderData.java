package br.com.saulo.cleancommerce.data.entities;

import br.com.saulo.cleancommerce.core.domain.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private Double total;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "order_item")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_item_id", nullable = false)
    private List<OrderItemData> orderItems;

    public static OrderData from(Order order) {
        return new OrderData(
                null,
                order.getTotal(),
                order.getCreatedAt(),
                OrderItemData.from(order.getOrderItemList())
        );
    }


    public void addOrderItem(OrderItemData orderItem) {
        if (this.orderItems == null) {
            this.orderItems = new ArrayList<>();
        }

        orderItem.setOrderData(this);
        this.orderItems.add(orderItem);
        this.calculateTotal();
    }

    private void calculateTotal() {
        this.total = this.orderItems
                .stream()
                .mapToDouble(OrderItemData::getTotal)
                .sum();
    }

}
