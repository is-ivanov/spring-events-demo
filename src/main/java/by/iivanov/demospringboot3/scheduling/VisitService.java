package by.iivanov.demospringboot3.scheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class VisitService {

    private final VisitRepository visitRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional(label = "saveVisit")
    void saveVisit() {
        Visit entity = Visit.create(VisitType.WELLNESS_VISIT);
        log.info("After Visit.create and before visitRepository.save");
        visitRepository.save(entity);
        log.warn("After visitRepository.save(entity) and before publish");
        applicationEventPublisher.publishEvent(new VisitCreatedEvent(entity.getType(), "Service event"));
        log.warn("After applicationEventPublisher.publishEvent");
    }

    @Transactional(label = "saveVisitFail")
    void saveVisitWithFail() {
        Visit entity = Visit.create(VisitType.WELLNESS_VISIT);
        log.info("After Visit.create and before visitRepository.save");
        visitRepository.saveWithFail(entity);
        log.warn("After visitRepository.save(entity) and before publish");
        applicationEventPublisher.publishEvent(new VisitCreatedEvent(entity.getType(), "Service event"));
        log.warn("After applicationEventPublisher.publishEvent");
    }

    @Transactional(label = "saveVisitSuccessButNotificationFail")
    void saveVisitSuccessButNotificationWithFail() {
        Visit entity = Visit.createWithFailNotification(VisitType.WELLNESS_VISIT);
        log.info("After Visit.create and before visitRepository.save");
        visitRepository.save(entity);
        log.warn("After visitRepository.save(entity) and before publish");
        applicationEventPublisher.publishEvent(new VisitCreatedFailNotificationEvent(entity.getType(), "Service event"));
        log.warn("After applicationEventPublisher.publishEvent");
    }
}
