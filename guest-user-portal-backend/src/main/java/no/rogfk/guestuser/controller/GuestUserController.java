package no.rogfk.guestuser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import no.rogfk.guestuser.model.GuestUser;
import no.rogfk.guestuser.model.GuestUserCreateStatus;
import no.rogfk.guestuser.service.GuestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Api(tags = "GuestUser")
@CrossOrigin()
@RequestMapping(value = "/api/guest")
public class GuestUserController {

    @Autowired
    GuestUserService guestUserService;

    @ApiOperation("Request new guest user")
    @RequestMapping(value = "user",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity createGuestUser(@RequestBody GuestUser guestUser,
                                          @RequestParam(value = "notifyHost", defaultValue = "false") Boolean notifyHost,
                                          @RequestParam(value = "notifyGuest", defaultValue = "false") Boolean notifyGuest) {
        log.info("GuestUser: {}", guestUser);
        GuestUserCreateStatus guestUserCreateStatus = guestUserService.create(guestUser, notifyHost, notifyGuest);
        if (guestUserCreateStatus != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(guestUserCreateStatus);
        } else {
            guestUserCreateStatus = new GuestUserCreateStatus();
            guestUserCreateStatus.setErrorMessage("Du er allerede registrert som gjest.");
            guestUserCreateStatus.setTimeout(5);
            return ResponseEntity.status(HttpStatus.FOUND).body(guestUserCreateStatus);
        }
    }
}
