package no.rogfk.guestuser.service

import no.rogfk.guestuser.model.EmployeeNotify
import no.rogfk.guestuser.model.GuestUserCreateStatus
import no.rogfk.guestuser.model.NotifyStatus
import no.rogfk.sms.SmsService
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.support.LdapNameBuilder
import spock.lang.Specification

import javax.naming.ldap.LdapName

class EmployeeNotifyServiceSpec extends Specification {

    private ldapTemplate
    private employeeNotifyService
    private configService
    private smsService

    void setup() {
        ldapTemplate = Mock(LdapTemplate)
        smsService = Mock(SmsService)
        configService = new ConfigService(hostMessage: "hostMessage", hostNotifiedMessage: "hostNotifiedMessage", unableToNotifyHostMessage: "unableToNotifyHostMessage")
        employeeNotifyService = new EmployeeNotifyService(ldapTemplate: ldapTemplate, configService: configService, smsService: smsService)
    }

    def "Notify employee"() {
        given:
        def guestUserCreateStatus = new GuestUserCreateStatus()

        when:
        employeeNotifyService.notifyEmployee("cn=ola", "Per Svendsen", guestUserCreateStatus)

        then:
        guestUserCreateStatus.hostMessage == "hostNotifiedMessage"
        guestUserCreateStatus.hostNotifyStatus == NotifyStatus.NOTIFIED
        1 * ldapTemplate.findByDn(_ as LdapName, _ as Class) >> new EmployeeNotify(dn: LdapNameBuilder.newInstance("cn=dn1").build(), fullname: "Per Svendsen", mobile: "99999999")
        1 * smsService.sendSms(_ as String, _ as String) >> '>true<'

    }
}
