package by.iivanov.demospringboot3.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    Logger log = LoggerFactory.getLogger(NotificationRepository.class);

    default void saveWithFail(Notification notification) {
        log.warn("Fail when notification saving");
        throw new IllegalStateException("Save notification failed");
    }
}
