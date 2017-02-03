package no.rogfk.guestuser.service

import no.rogfk.guestuser.model.GuestUser
import spock.lang.Specification

class HistoryServiceSpoc extends Specification {

    private historyService
    private guestUserService

    void setup() {
        guestUserService = Mock(GuestUserService)
        historyService = new HistoryService(guestUserService: guestUserService)

    }

    def "Historize All Guests"() {
        when:
        historyService.historizeAllGuests()

        then:
        1 * guestUserService.getTodaysGuests() >> Arrays.asList(new GuestUser(), new GuestUser())
        2 * guestUserService.historizeGuestUser(_ as GuestUser)
    }

    def "Historize Old Guests"() {
        when:
        historyService.historizeOldGuests()

        then:
        1 * guestUserService.getTodaysGuests() >> Arrays.asList(new GuestUser(dateOfVisit: "20000122020000Z"))
        1 * guestUserService.historizeGuestUser(_ as GuestUser)
    }

    def "Check if date of visit is older than today"() {
        given:
        def historyService = new HistoryService()
        def dateOfVisit = "20000122020000Z"

        when:
        def visitOlderThanToday = historyService.isVisitDateOlderThanToday(dateOfVisit)

        then:
        visitOlderThanToday == true
    }

    def "Check if date of visit is not older than today"() {
        given:
        def historyService = new HistoryService()
        def dateOfVisit = "20990122020000Z"

        when:
        def visitOlderThanToday = historyService.isVisitDateOlderThanToday(dateOfVisit)

        then:
        visitOlderThanToday == false
    }
}
