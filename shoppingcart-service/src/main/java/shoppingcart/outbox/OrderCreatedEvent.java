package shoppingcart.outbox;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.UUID;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import shoppingcart.dto.ProductDTO;
import shoppingcart.dto.ShoppingCartDTO;
import shoppingcart.entities.Product;
import shoppingcart.entities.ShoppingCart;

public class OrderCreatedEvent implements ExportedEvent {

    private static ObjectMapper mapper = new ObjectMapper();

    private final Long id;
    private final JsonNode order;
    private final Instant timestamp;

    private OrderCreatedEvent(Long id, JsonNode order) {
        this.id = id;
        this.order = order;
        this.timestamp = Instant.now();
    }

    public static OrderCreatedEvent of(ShoppingCartDTO order) {
        ObjectNode asJson = mapper.createObjectNode()
                .put("id", String.valueOf(UUID.randomUUID()))
                .put("userId", order.getUserId())
                .put("totalPrice", order.getTotalPrice())
                .put("createdTimestamp", order.getCreatedTimestamp().toString());

        ArrayNode items = asJson.putArray("lineItems");

        for (ProductDTO product : order.getProducts()) {
            ObjectNode lineAsJon = mapper.createObjectNode()
                    .put("productId", product.getProductId())
                    .put("quantity", product.getQuantity());

            items.add(lineAsJon);
        }

        return new OrderCreatedEvent(order.getId(), asJson);
    }

    @Override
    public String getAggregateId() {
        return String.valueOf(id);
    }

    @Override
    public String getAggregateType() {
        return "Order";
    }

    @Override
    public JsonNode getPayload() {
        return order;
    }

    @Override
    public String getType() {
        return "OrderCreated";
    }

    @Override
    public Instant getTimestamp() {
        return timestamp;
    }
}

