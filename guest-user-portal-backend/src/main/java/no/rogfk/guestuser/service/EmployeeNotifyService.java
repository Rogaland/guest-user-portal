package no.rogfk.guestuser.service;

import no.rogfk.guestuser.model.EmployeeNotify;
import no.rogfk.guestuser.model.GuestUserCreateStatus;
import no.rogfk.guestuser.model.NotifyStatus;
import no.rogfk.sms.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import javax.naming.directory.SearchControls;

@Service
public class EmployeeNotifyService {

    @Autowired
    LdapTemplate ldapTemplate;

    @Autowired
    SmsService smsService;

    @Autowired
    ConfigService configService;

    private SearchControls searchControls;


    public EmployeeNotifyService() {
        searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
    }

    public void notifyEmployee(String dn, String guestName, GuestUserCreateStatus guestUserCreateStatus) {
        EmployeeNotify employeeNotify = getEmployee(dn);

        try {
            String notifyHostResponse = smsService.sendSms(
                    String.format(configService.getHostMessage(), guestName),
                    employeeNotify.getMobile()
            );

            if (notifyHostResponse.contains(">true<")) {
                guestUserCreateStatus.setHostNotifyStatus(NotifyStatus.NOTIFIED);
                guestUserCreateStatus.setHostMessage(
                        String.format(configService.getHostNotifiedMessage(), employeeNotify.getFullname())
                );
                guestUserCreateStatus.setTimeout(5);
            } else {
                guestUserCreateStatus.setGuestNotifyStatus(NotifyStatus.UNABLE_TO_NOTIFY);
                guestUserCreateStatus.setHostMessage(
                        String.format(configService.getUnableToNotifyHostMessage(), employeeNotify.getFullname())
                );
                guestUserCreateStatus.setTimeout(20);
            }
        } catch (ResourceAccessException e) {
            guestUserCreateStatus.setGuestNotifyStatus(NotifyStatus.UNABLE_TO_NOTIFY);
            guestUserCreateStatus.setHostMessage(
                    String.format(configService.getUnableToNotifyHostMessage(), employeeNotify.getFullname())
            );
            guestUserCreateStatus.setTimeout(20);
        }
    }

    private EmployeeNotify getEmployee(String dn) {
        return ldapTemplate.findByDn(LdapNameBuilder.newInstance(dn).build(), EmployeeNotify.class);
    }


}
