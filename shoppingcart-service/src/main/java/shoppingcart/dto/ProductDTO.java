package shoppingcart.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@ToString
@AllArgsConstructor
@EqualsAndHashCode
public class ProductDTO implements Serializable {
    private Long id;
    private Long productId;
    private int quantity;
    private Long shoppingCartId;
}
