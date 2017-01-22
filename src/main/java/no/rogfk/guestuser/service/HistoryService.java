package no.rogfk.guestuser.service;

import no.rogfk.guestuser.model.GuestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void historizeYesterdaysGuests() {

    }
}
