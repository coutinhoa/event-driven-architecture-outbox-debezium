package shoppingcart.entities;

import javax.persistence.*;
import lombok.*;

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

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shopping_cart_id", nullable = false)
    private ShoppingCart shopping_cart;
}