package main.java.no.rogfk.guestuser.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import main.java.no.rogfk.guestuser.model.GuestUser;
import main.java.no.rogfk.guestuser.service.GuestUserService;
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
    public ResponseEntity<String> createGuestUser(@ModelAttribute GuestUser guestUser) {
        log.info("GuestUser: {}", guestUser);

        guestUserService.create(guestUser);
        return new ResponseEntity<>(HttpStatus.CREATED);


    }


}
