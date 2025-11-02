package by.iivanov.demospringboot3.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {

    Logger log = LoggerFactory.getLogger(VisitRepository.class);

    default void saveWithFail(Visit entity) {
        log.warn("Fail when visit saving");
        throw new IllegalStateException("Save visit failed");
    }
}
