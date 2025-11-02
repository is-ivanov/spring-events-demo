package by.iivanov.demospringboot3.scheduling;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "visit")
@ToString
public class Visit extends AbstractAggregateRoot<Visit> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated
    private VisitType type;

    private Instant created;


    protected Visit() {
    }

    static Visit create(VisitType type) {
        Visit visit = new Visit();
        visit.type = type;
        visit.created = Instant.now();
        visit.registerEvent(new VisitCreatedEvent(visit.getType(), "Domain event"));
        return visit;
    }

    public static Visit createWithFailNotification(VisitType type) {
        Visit visit = new Visit();
        visit.type = type;
        visit.created = Instant.now();
        visit.registerEvent(new VisitCreatedFailNotificationEvent(visit.getType(), "Domain event"));
        return visit;
    }
}
