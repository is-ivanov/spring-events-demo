package by.iivanov.demospringboot3.scheduling;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "notification")
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String message;

    private Instant created;

    public Notification(String message) {
        this.message = message;
        this.created = Instant.now();
    }
}
