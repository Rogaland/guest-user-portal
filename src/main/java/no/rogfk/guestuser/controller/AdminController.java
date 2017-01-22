package no.rogfk.guestuser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import no.rogfk.guestuser.model.GuestUser;
import no.rogfk.guestuser.service.GuestUserService;
import no.rogfk.guestuser.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@Api(tags = "GuesUserAdmin")
@CrossOrigin()
@RequestMapping(value = "/admin/guest")
public class AdminController {

    @Autowired
    GuestUserService guestUserService;

    @Autowired
    HistoryService historyService;

    @ApiOperation("Get all todays guests")
    @RequestMapping(value = "today",
            method = RequestMethod.GET)
    public Collection<GuestUser> getTodaysGuestUsers() {
        return guestUserService.getTodaysGuests();
    }

    @ApiOperation("Get all historical guests")
    @RequestMapping(value = "historical",
            method = RequestMethod.GET)
    public Collection<GuestUser> getHistoricalGuestUsers() {
        return guestUserService.getHistoricalGuests();
    }

    @ApiOperation("Historize all guests")
    @RequestMapping(value = "historical",
            method = RequestMethod.POST)
    public ResponseEntity historizeAllGuests() {

        historyService.historizeAllGuests();
        return ResponseEntity.ok().build();
    }

    @ApiOperation("Get all guests")
    @RequestMapping(method = RequestMethod.GET)
    public Collection<GuestUser> getAllGuestUsers() {
        return guestUserService.getAllGuests();
    }

}
