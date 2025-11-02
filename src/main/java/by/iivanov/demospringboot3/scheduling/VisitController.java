package by.iivanov.demospringboot3.scheduling;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping("/visit")
    public void createVisit () {
        visitService.saveVisit();
    }

    @PostMapping("/visit-failed")
    public void createVisitFailed() {
        visitService.saveVisitWithFail();
    }

    @PostMapping("/visit-notification-failed")
    public void createVisitAndNotificationFailed() {
        visitService.saveVisitSuccessButNotificationWithFail();
    }
}
