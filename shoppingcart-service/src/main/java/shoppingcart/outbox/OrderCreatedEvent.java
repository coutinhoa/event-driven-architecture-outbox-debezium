package shoppingcart.outbox;

import java.time.Instant;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import shoppingcart.entities.Product;
import shoppingcart.entities.ShoppingCart;

public class OrderCreatedEvent implements ExportedEvent {

    private static ObjectMapper mapper = new ObjectMapper();

    private final long id;
    private final JsonNode order;
    private final Instant timestamp;

    private OrderCreatedEvent(long id, JsonNode order) {
        this.id = id;
        this.order = order;
        this.timestamp = Instant.now();
    }

    public static OrderCreatedEvent of(ShoppingCart order) {
        ObjectNode asJson = mapper.createObjectNode()
                .put("id", order.getId())
                .put("userId", order.getUserId())
                .put("totalPrice", order.getTotalPrice())
                .put("createdTimestamp", order.getCreatedTimestamp().toString());

        ArrayNode items = asJson.putArray("lineItems");

        for (Product product : order.getProducts()) {
            ObjectNode lineAsJon = mapper.createObjectNode()
                    .put("productId", product.getId())
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

