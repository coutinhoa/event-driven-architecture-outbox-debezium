package order.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Entity
@Getter
@Setter
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "product_id", nullable = false)
    private Long productId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable = false)
    private Order order;
}