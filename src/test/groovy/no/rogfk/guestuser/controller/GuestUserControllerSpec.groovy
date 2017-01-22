package no.rogfk.guestuser.controller

import no.rogfk.guestuser.model.GuestUser
import spock.lang.Specification


class GuestUserControllerSpec extends Specification {
    def "Request new guest user"() {
        given:
            def controller = new GuestUserController()
            def guestUser = new GuestUser();


        when:
            def res = controller.createGuestUser(guestUser)
        then:
            res.getDateOfVisit() != null;
            res.getDn() == null;
    }

    def "Request all guest users"() {
        given:
            def controller = new GuestUserController();

        when:
            def res = controller.getGuestUsers();

        then:
            res.size() >= 0

    }
}
