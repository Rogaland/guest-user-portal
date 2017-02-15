package no.rogfk.guestuser.service

import no.rogfk.guestuser.model.EmployeeNotify
import no.rogfk.guestuser.model.GuestUserCreateStatus
import no.rogfk.sms.SmsService
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.support.LdapNameBuilder
import spock.lang.Specification

import javax.lang.model.element.Name


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
        1 * ldapTemplate.findByDn(_ as Name, _ as EmployeeNotify) >> new EmployeeNotify(dn: LdapNameBuilder.newInstance("cn=dn1").build(), fullname: "Per Svendsen", mobile: "99999999")


    }
}
