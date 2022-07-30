package br.com.saulo.cleancommerce.data.entities;

import br.com.saulo.cleancommerce.core.domain.Order;
import br.com.saulo.cleancommerce.core.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private Double total;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "orderData")
    private List<OrderItemData> orderItems;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    private CustomerData customerData;

    public static OrderData from(Order order) {
        return new OrderData(
                order.getId(),
                order.getTotal(),
                order.getCreatedAt(),
                new ArrayList<>(),
                order.getStatus(),
                CustomerData.fromCustomer(order.getCustomer())
        );
    }

    public static OrderData newInstance(CustomerData customerData) {
        return new OrderData(
                null,
                0d,
                Instant.now(),
                new ArrayList<>(),
                Status.OPEN,
                customerData
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
                .mapToDouble(OrderItemData::getUnitPrice)
                .sum();
    }

}
