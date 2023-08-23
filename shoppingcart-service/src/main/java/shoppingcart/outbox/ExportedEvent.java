package shoppingcart.outbox;

import java.time.Instant;

import com.fasterxml.jackson.databind.JsonNode;

public interface ExportedEvent {
    String getAggregateId();
    String getAggregateType();
    String getType();
    Instant getTimestamp();
    JsonNode getPayload();
}
