package br.com.saulo.cleancommerce.data.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "orders")
public class OrderData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private Long total;
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "order_item")
    @OneToMany
    @JoinColumn(name = "order_item_id", nullable = false)
    private List<OrderItemData> orderItemData;
}
