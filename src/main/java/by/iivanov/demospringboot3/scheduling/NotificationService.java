package by.iivanov.demospringboot3.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Transactional(label = "sendNotification")
    void sendNotification(String message) {
        log.info("Saving notification: {}", message);
        notificationRepository.save(new Notification(message));
    }

    @Transactional(label = "sendNotificationFail")
    void sendNotificationFail(String message) {
        log.info("Saving notification: {}", message);
        notificationRepository.saveWithFail(new Notification(message));
    }
}
