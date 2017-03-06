package no.rogfk.guestuser.service

import spock.lang.Specification


class SchedulingServiceSpec extends Specification {

    def "Historize guests"() {
        given:
        def historyService = Mock(HistoryService)
        def schedulingService = new SchedulingService(historyService: historyService)

        when:
        schedulingService.historizeOldGuests()

        then:
        1 * historyService.historizeOldGuests()
    }
}
