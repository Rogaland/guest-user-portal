package no.rogfk.guestuser.service;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;

@Data
@Service
public class ConfigService {

    @Value("${rfk.guest.search.min-query-length:3}")
    public int minQueryLength;

    @Value("${rfk.ldap.url}")
    private String ldapHostUrl;

    @Value("${rfk.ldap.user}")
    private String ldapUser;

    @Value("${rfk.ldap.password}")
    private String ldapPassword;

    @Value("${rfk.guest.base-container}")
    private String guestBaseContainer;

    @Value("${rfk.guest.historical-container}")
    private String histGuestContainer;

    @Value("${rfk.guest.today-container}")
    private String todaysGuestContainer;

    @Value("${rfk.message.sms.guest}")
    private String guestMessage;

    @Value("${rfk.message.sms.host}")
    private String hostMessage;

    @Value("${rfk.message.ui.unable-to-notify-host}")
    private String unableToNotifyHostMessage;

    @Value("${rfk.message.ui.host-notified}")
    private String hostNotifiedMessage;

    @Value("${rfk.message.ui.unable-to-notify-guest}")
    private String unableToNotifyGuestMessage;

    @Value("${rfk.message.ui.guest-notified}")
    private String guestNotifiedMessage;


    public Name getGuestBase() {
        return LdapNameBuilder.newInstance(guestBaseContainer).build();
    }

    public Name getHistGuestBase() {
        return LdapNameBuilder.newInstance(String.format(histGuestContainer, guestBaseContainer)).build();
    }

    public Name getTodaysGuestBase() {
        return LdapNameBuilder.newInstance(String.format(todaysGuestContainer, guestBaseContainer)).build();
    }


}
