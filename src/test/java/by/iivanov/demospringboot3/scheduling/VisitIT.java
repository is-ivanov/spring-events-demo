package by.iivanov.demospringboot3.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.BDDSoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Slf4j
@Testcontainers
@ExtendWith(SoftAssertionsExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class VisitIT {

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> db = new PostgreSQLContainer<>("postgres:16.8");

    @Autowired
    TestRestTemplate restTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    BeanPropertyRowMapper<Visit> visitRowMapper = new BeanPropertyRowMapper<>(Visit.class);
    BeanPropertyRowMapper<Notification> notificationRowMapper = new BeanPropertyRowMapper<>(Notification.class);

    @InjectSoftAssertions
    private BDDSoftAssertions softly;

    @BeforeEach
    void init() {
        jdbcTemplate.execute("TRUNCATE TABLE visit");
        jdbcTemplate.execute("TRUNCATE TABLE notification");
        jdbcTemplate.execute("TRUNCATE TABLE event_publication");
    }

    @Test
    void successTest() throws InterruptedException {
        log.warn("===== START TEST =====");
        // GIVEN:
        // WHEN:
        restTemplate.postForLocation("/visit", null);
        // THEN:
        log.warn("===== ALL VISITS IN DATABASE =====");
        List<Visit> visits = jdbcTemplate.query("SELECT * FROM visit", visitRowMapper);
        visits.forEach(it -> log.warn("Visit: {}", it));
        Thread.sleep(600L);
        log.warn("===== ALL NOTIFICATIONS IN DATABASE =====");
        List<Notification> notifications = jdbcTemplate.query("SELECT * FROM notification", notificationRowMapper);
        notifications.forEach(it -> log.warn("Notification: {}", it));
        log.warn("===== ALL EVENT_PUBLICATIONS IN DATABASE =====");
        Thread.sleep(600L);
        jdbcTemplate.queryForList("SELECT * FROM event_publication").forEach(it -> log.warn("Event publication: {}", it));

        softly.then(notifications).hasSize(2);
        softly.then(visits).hasSize(1);
    }

    @Test
    void saveVisitWithFailTest() throws InterruptedException {
        log.warn("===== START TEST =====");
        // GIVEN:
        // WHEN:
        restTemplate.postForLocation("/visit-failed", null);
        // THEN:
        log.warn("===== ALL VISITS IN DATABASE =====");
        List<Visit> visits = jdbcTemplate.query("SELECT * FROM visit", visitRowMapper);
        visits.forEach(it -> log.warn("Visit: {}", it));
        Thread.sleep(600L);
        log.warn("===== ALL NOTIFICATIONS IN DATABASE =====");
        List<Notification> notifications = jdbcTemplate.query("SELECT * FROM notification", notificationRowMapper);
        notifications.forEach(it -> log.warn("Notification: {}", it));
        log.warn("===== ALL EVENT_PUBLICATIONS IN DATABASE =====");
        Thread.sleep(600L);
        jdbcTemplate.queryForList("SELECT * FROM event_publication").forEach(it -> log.warn("Event publication: {}", it));

        softly.then(notifications).isEmpty();
        softly.then(visits).isEmpty();

    }

    @Test
    void saveVisitSuccessButNotificationWithFailTest() throws InterruptedException {
        log.warn("===== START TEST =====");
        // GIVEN:
        // WHEN:
        restTemplate.postForLocation("/visit-notification-failed", null);
        // THEN:
        log.warn("===== ALL VISITS IN DATABASE =====");
        List<Visit> visits = jdbcTemplate.query("SELECT * FROM visit", visitRowMapper);
        visits.forEach(it -> log.warn("Visit: {}", it));
        Thread.sleep(600L);
        log.warn("===== ALL NOTIFICATIONS IN DATABASE =====");
        List<Notification> notifications = jdbcTemplate.query("SELECT * FROM notification", notificationRowMapper);
        notifications.forEach(it -> log.warn("Notification: {}", it));
        log.warn("===== ALL EVENT_PUBLICATIONS IN DATABASE =====");
        Thread.sleep(600L);
        jdbcTemplate.queryForList("SELECT * FROM event_publication").forEach(it -> log.warn("Event publication: {}", it));

        softly.then(notifications).isEmpty();
        softly.then(visits).isEmpty();
    }

}
