package shoppingcart.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@Entity
@Table(name = "shopping_cart")
@NoArgsConstructor
public class ShoppingCart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name= "user_id", nullable= false)
    private Long userId;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "created_timestamp", nullable = false)
    private LocalDateTime createdTimestamp;

    @OneToMany(mappedBy = "shopping_cart", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();
}
