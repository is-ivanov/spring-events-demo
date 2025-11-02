package by.iivanov.demospringboot3.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class NotificationFailListener {

    private final NotificationService notificationService;

    public NotificationFailListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

//    @EventListener
//    void handleEvent(VisitCreatedFailNotificationEvent event) throws InterruptedException {
//        log.info("Received event: {}", event);
//        Thread.sleep(500L);
//        notificationService.sendNotificationFail("@EventListener: " + event.evenSource());
//    }

//    @Async
//    @EventListener
//    void handleAsyncEvent(VisitCreatedFailNotificationEvent event) throws InterruptedException {
//        log.info("Received async event: {}", event);
//        Thread.sleep(500L);
//        notificationService.sendNotificationFail("@Async @EventListener: " + event.evenSource());
//    }

//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    void handleTransactionalAfterCommitEvent(VisitCreatedFailNotificationEvent event) throws InterruptedException {
//        log.info("Received transactional AFTER COMMIT event: {}", event);
//        Thread.sleep(500L);
//        notificationService.sendNotificationFail("@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT): " + event.evenSource());
//    }

//    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
//    void handleTransactionalBeforeCommitEvent(VisitCreatedFailNotificationEvent event) throws InterruptedException {
//        log.info("Received transactional BEFORE COMMIT event: {}", event);
//        Thread.sleep(500L);
//        notificationService.sendNotificationFail("@TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT): " + event.evenSource());
//    }

//    @Async
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    void handleAsyncTransactionalAfterCommitEvent(VisitCreatedFailNotificationEvent event) throws InterruptedException {
//        log.info("Received async transactional AFTER COMMIT event: {}", event);
//        Thread.sleep(500L);
//        notificationService.sendNotificationFail("@Async @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT): " + event.evenSource());
//    }

//    @Async
//    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
//    void handleAsyncTransactionalBeforeCommitEvent(VisitCreatedFailNotificationEvent event) throws InterruptedException {
//        log.info("Received async transactional BEFORE COMMIT event: {}", event);
//        Thread.sleep(500L);
//        notificationService.sendNotificationFail("@Async @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT): " + event.evenSource());
//    }

    @ApplicationModuleListener
    void handleApplicationModuleEvent(VisitCreatedFailNotificationEvent event) throws InterruptedException {
        log.info("Received application module event: {}", event);
        Thread.sleep(500L);
        notificationService.sendNotificationFail("@ApplicationModuleListener: " + event.evenSource());
    }

}
