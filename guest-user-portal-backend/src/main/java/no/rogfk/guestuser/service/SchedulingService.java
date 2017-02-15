package no.rogfk.guestuser.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SchedulingService {

    @Autowired
    private HistoryService historyService;

    @Scheduled(cron = "0 1 * * * ?")
    private void historizeOldGuests() {
        log.info("Historize old guests");
        historyService.historizeOldGuests();
    }
}
