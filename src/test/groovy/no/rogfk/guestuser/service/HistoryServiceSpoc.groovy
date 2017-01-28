package no.rogfk.guestuser.service

import spock.lang.Specification

class HistoryServiceSpoc extends Specification {

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
