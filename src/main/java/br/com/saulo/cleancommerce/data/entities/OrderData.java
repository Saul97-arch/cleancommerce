package br.com.saulo.cleancommerce.data.entities;

import br.com.saulo.cleancommerce.core.domain.Order;
import br.com.saulo.cleancommerce.core.domain.Status;
import br.com.saulo.cleancommerce.data.entities.dto.OrderRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "order")
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
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<OrderItemData> orderItems;

    private Status status;

    public OrderData(Double total) {
        this.total = total;
    }
    // TODO include this field in customer
    /*
    @OneToMany
    @Column(name = "customer")
    private CustomerData customerData;
    */
    public static OrderData from(Order order) {
        return new OrderData(
                order.getId(),
                order.getTotal(),
                order.getCreatedAt(),
                new ArrayList<>(),
                order.getStatus()
        );
    }

    public static OrderData newInstance(List<OrderItemData> orderItems) {
        OrderData order = new OrderData(
                null,
                0d,
                Instant.now(),
                null,
                Status.OPEN
        );

        orderItems.forEach(order::addOrderItem);

        return order;
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
