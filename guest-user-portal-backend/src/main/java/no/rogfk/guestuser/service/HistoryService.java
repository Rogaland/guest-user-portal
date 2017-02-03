package main.java.no.rogfk.guestuser.service;

import lombok.extern.slf4j.Slf4j;
import main.java.no.rogfk.guestuser.model.GuestUser;
import no.rogfk.ldap.utilities.LdapTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
public class HistoryService {

    @Autowired
    GuestUserService guestUserService;

    public void historizeAllGuests() {
        List<GuestUser> guestUsers = guestUserService.getTodaysGuests();

        guestUsers.forEach(guestUser -> {
            guestUserService.historizeGuestUser(guestUser);
        });
    }

    public void historizeOldGuests() {
        List<GuestUser> guestUsers = guestUserService.getTodaysGuests();

        guestUsers.forEach(guestUser -> {
            if (isVisitDateOlderThanToday(guestUser.getDateOfVisit())) {
                log.info("Historize guest user.");
                guestUserService.historizeGuestUser(guestUser);
            }
        });

    }

    public boolean isVisitDateOlderThanToday(String visitDate) {
        LocalDate dateOfVisit = LocalDate.parse(visitDate, DateTimeFormatter.ofPattern(LdapTimestamp.LDAP_TIMESTAMP_FORMAT));
        return dateOfVisit.isBefore(LocalDate.now());
    }

}
