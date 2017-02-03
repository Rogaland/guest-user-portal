package no.rogfk.guestuser.service

import no.rogfk.guestuser.model.GuestUser
import org.springframework.ldap.NameNotFoundException
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.support.LdapNameBuilder
import spock.lang.Specification

import javax.naming.Name

class GuestUserServiceSpec extends Specification {
    private configService
    private guestUserObjectService
    private ldapTemplate
    private guestUserService

    void setup() {
        configService = new ConfigService(todaysGuestContainer: "o=gjest", histGuestContainer: "o=hist", guestBaseContainer: "o=guest")
        guestUserObjectService = new GuestUserObjectService(configService: configService)
        ldapTemplate = Mock(LdapTemplate)
        guestUserService = new GuestUserService(configService: configService,
                guestUserObjectService: guestUserObjectService,
                ldapTemplate: ldapTemplate)
    }

    def "Create GuestUser"() {
        given:
        def guestUser1 = new GuestUser(mobile: "00000000")
        def guestUser2 = new GuestUser(mobile: "11111111")

        when:
        def created1 = guestUserService.create(guestUser1)
        def created2 = guestUserService.create(guestUser2)

        then:
        created1 == true
        created2 == false
        ldapTemplate.lookup(_ as Name) >> { throw new NameNotFoundException("test") } >> null
        1 * ldapTemplate.create(_ as GuestUser)
    }

    def "Update GuestUser"() {
        given:
        def guestUser1 = new GuestUser(dn: LdapNameBuilder.newInstance("cn=dn1").build())
        def guestUser2 = new GuestUser(dn: LdapNameBuilder.newInstance("cn=dn2").build())

        when:
        def updated1 = guestUserService.update(guestUser1)
        def updated2 = guestUserService.update(guestUser2)

        then:
        updated1 == false
        updated2 == true
        ldapTemplate.lookup(_ as Name) >> { throw new NameNotFoundException("test") } >> null
        1 * ldapTemplate.update(_ as GuestUser)
    }

    def "Historize GuestUser"() {
        given:
        def guestUser = new GuestUser(dn: LdapNameBuilder.newInstance("cn=9999,o=guest").build())

        when:
        def histGuestUser = guestUserService.historizeGuestUser(guestUser)

        then:
        histGuestUser.loginDisabled == true
        histGuestUser.cn != null
        histGuestUser.dn.contains("cn=9999") == false
        1 * ldapTemplate.create(_ as GuestUser)
        1 * ldapTemplate.delete(_ as GuestUser)
    }

    def "Get todays guests"() {
        given:
        true

        when:
        true
        then:
        true
    }

    def "Get historical guests"() {
        given:
        true

        when:
        true
        then:
        true
    }

    def "Get all guests"() {
        given:
        true

        when:
        true
        then:
        true
    }

    def "GuestUser exists"() {
        given:
        true

        when:
        true
        then:
        true
    }
}
