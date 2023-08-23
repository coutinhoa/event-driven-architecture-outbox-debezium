package shoppingcart.outbox;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="outboxevent")
public class Outbox {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String aggregateType;

    @Column(nullable = false)
    private String aggregateId;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String payload;

    @Column(updatable = false)
    private Instant timestamp;
}
