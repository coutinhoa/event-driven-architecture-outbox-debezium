package shoppingcart.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shoppingcart.dto.ProductDTO;
import shoppingcart.dto.ShoppingCartDTO;
import shoppingcart.entities.Product;
import shoppingcart.entities.ShoppingCart;
import shoppingcart.outbox.ExportedEvent;
import shoppingcart.outbox.OrderCreatedEvent;
import shoppingcart.outbox.Outbox;
import shoppingcart.outbox.OutboxRepository;
import shoppingcart.repositories.ShoppingCartRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ShoppingCartService {


    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    OutboxRepository outboxRepository;

    @Autowired
    ShoppingCartService(ShoppingCartRepository shoppingCartRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
    }
    public List<ShoppingCart> getAll(String name) {
        return shoppingCartRepository.findAll();
    }

    @Transactional
    public ShoppingCart addOrder(ShoppingCartDTO orderRequest) {
        System.out.println(orderRequest);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setTotalPrice(orderRequest.getTotalPrice());
        shoppingCart.setUserId(orderRequest.getUserId());

        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : orderRequest.getProducts()) {
            Product product = new Product();
            product.setQuantity(productDTO.getQuantity());
            product.setProductId(productDTO.getProductId());
            product.setShopping_cart(shoppingCart);
            products.add(product);
        }

        shoppingCart.setProducts(products);

        final OrderCreatedEvent orderCreatedEvent = OrderCreatedEvent.of(orderRequest);
        System.out.println("orderCreatedEvent: "+ orderCreatedEvent.getPayload());
        this.outboxRepository.save(of(orderCreatedEvent));
        return shoppingCartRepository.save(shoppingCart);
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
        System.out.println("outbox");
        return outbox;
    }
}
