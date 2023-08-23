package shoppingcart.services;

import java.util.UUID;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppingcart.entities.ShoppingCart;
import shoppingcart.outbox.*;
import shoppingcart.repositories.ShoppingCartRepository;

@Service
public class OrderService {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    OutboxRepository outboxRepository;

    @Transactional
    public ShoppingCart addOrder(ShoppingCart orderRequest) {
        /*ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setTotalPrice(orderRequest.getTotalPrice());
        shoppingCart.setUserId(orderRequest.getUserId());

        List<Product> products = new ArrayList<>();
        for (Product productDTO : orderRequest.getProducts()) {
            Product product = new Product();
            product.setQuantity(productDTO.getQuantity());
            product.setProductId(productDTO.getProductId());
            product.setShopping_cart(shoppingCart);
            products.add(product);
        }

        shoppingCart.setProducts(products);*/
        orderRequest = this.shoppingCartRepository.save(orderRequest);
        System.out.println("saved order in cart repository");

        final OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.of(orderRequest);
        this.outboxRepository.save(of(orderCreatedEvent));
        System.out.println("saved order in outbox repository");

        System.out.println("order request:" + orderRequest);
        return orderRequest;
    }

    private static Outbox of(ExportedEvent exportedEvent) {
        Outbox outbox = new Outbox();
        outbox.setId(UUID.randomUUID());
        outbox.setAggregateId(exportedEvent.getAggregateId());
        outbox.setAggregateType(exportedEvent.getAggregateType());
        outbox.setType(exportedEvent.getType());
        outbox.setTimestamp(exportedEvent.getTimestamp());
        try {
            outbox.setPayload(mapper.writeValueAsString(exportedEvent.getPayload()));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return outbox;
    }
}
