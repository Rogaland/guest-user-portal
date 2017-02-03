package test.groovy.no.rogfk.guestuser.service

import main.java.no.rogfk.guestuser.exception.MissingMandatoryAttribute
import main.java.no.rogfk.guestuser.model.GuestUser
import spock.lang.Specification

class GuestUserObjectServiceSpec extends Specification {

    private configService
    private guestUserObjectService

    void setup() {
        configService = new ConfigService(todaysGuestContainer: "o=gjest", histGuestContainer: "o=hist")
        guestUserObjectService = new GuestUserObjectService(configService: configService)
    }

    def "Setup GuestUser object with mandatory attributes set"() {
        given:
        def guestUser = new GuestUser(mobile: "00000000")

        when:
        guestUserObjectService.setupTodaysGuestUser(guestUser)

        then:
        guestUser.dateOfVisit != null
        guestUser.cn != null
        guestUser.dn != null
        guestUser.loginDisabled == false
    }

    def "Setup GuestUser object without mandatory attributes set"() {
        given:
        def guestUser = new GuestUser()

        when:
        guestUserObjectService.setupTodaysGuestUser(guestUser)

        then:
        thrown(MissingMandatoryAttribute)
    }

    def "Setup historical GuestUser"() {
        given:
        def guestUser = new GuestUser()

        when:
        guestUserObjectService.setupHistoricalGuestUser(guestUser)

        then:
        guestUser.cn != null
        guestUser.dn != null
        guestUser.loginDisabled == true
    }
}
