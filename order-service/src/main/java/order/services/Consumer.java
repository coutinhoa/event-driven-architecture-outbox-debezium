package order.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class Consumer {
    
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @KafkaListener(topics = "purchase", id = "foo")
    public void receive(@Payload String data) throws JsonMappingException, JsonProcessingException {

        JsonNode event = OBJECT_MAPPER.readValue(data, JsonNode.class);

        System.out.println("********* PROCESSING *********");
        System.out.println("User Id: " + event.get("userId").asText());

        event.get("lineItems").elements().forEachRemaining(node ->{
            System.out.println(node.get("productId").asText() + " " + node.get("quantity").asText());
        });

    }

}
