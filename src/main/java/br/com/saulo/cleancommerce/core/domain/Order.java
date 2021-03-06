package br.com.saulo.cleancommerce.core.domain;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Value
@EqualsAndHashCode(of = {"id"})
public class Order {
    Long id;
    Status status;
    List<OrderItem> orderItemList;
    Instant createdAt;
    Double total;
    Customer customer;
    public static Order newInstance(Customer customer) {
        return new Order(
                null,
                Status.OPEN,
                null,
                Instant.now(),
                null,
                customer
        );
    }

    private static Double calculateTotal(List<OrderItem> orderItems) {
        return orderItems
                .stream()
                .mapToDouble(OrderItem::getUnitPrice)
                .sum();
    }

    public Order delete() {
        if (this.status != Status.OPEN) {
            throw new IllegalStateException("Order should be open to be cancelled");
        }

        return newInstanceWith(Status.CANCELLED);
    }

    public Order pending() {
        if (this.status != Status.PAID) {
            throw new IllegalStateException("Order should be paid to be pen ding");
        }

        return newInstanceWith(Status.CANCELLED);
    }

    public Order delivery() {
        if (this.status != Status.PAID) {
            throw new IllegalStateException("Order should be paid to be delivered");
        }

        return newInstanceWith(Status.DELIVERED);
    }

    public Order pay() {
        if (this.status != Status.OPEN) {
            throw new IllegalStateException("Order should be open to be paid");
        }

        return newInstanceWith(Status.PAID);
    }

    private Order newInstanceWith(Status status) {
        return new Order(
                id,
                status,
                orderItemList,
                Instant.now(),
                total,
                customer
        );
    }
}
