package no.rogfk.guestuser.service;

import no.rogfk.guestuser.exception.MissingMandatoryAttribute;
import no.rogfk.guestuser.model.GuestUser;
import no.rogfk.guestuser.utilities.LdapConstants;
import no.rogfk.ldap.utilities.LdapTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import java.util.UUID;

@Service
public class GuestUserObjectService {

    @Autowired
    ConfigService configService;

    public void setupTodaysGuestUser(GuestUser guestUser) {

        if (guestUser.getMobile() == null || guestUser.getMobile().isEmpty()) {
            throw new MissingMandatoryAttribute("Missing mobile phone.");
        }

        Name dn = LdapNameBuilder.newInstance(
                configService.getTodaysGuestBase()).add(LdapConstants.CN, guestUser.getMobile()
        ).build();

        guestUser.setDateOfVisit(LdapTimestamp.getTimestampString());
        guestUser.setCn(guestUser.getMobile());
        guestUser.setDn(dn);
        guestUser.setLoginDisabled(false);
    }

    public void setupHistoricalGuestUser(GuestUser guestUser) {
        String cn = UUID.randomUUID().toString();
        Name dn = LdapNameBuilder.newInstance(configService.getHistGuestBase()).add(LdapConstants.CN, cn).build();

        guestUser.setCn(cn);
        guestUser.setDn(dn);
        guestUser.setLoginDisabled(true);
    }
}
