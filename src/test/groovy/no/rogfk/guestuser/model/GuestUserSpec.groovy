package no.rogfk.guestuser.model


class GuestUserSpec extends spock.lang.Specification {

    def "Create an empty GustUser object"() {
        when:
            def guestUser = new GuestUser()

        then:
            guestUser.getDn() == null
            guestUser.getDateOfVisit() != null
    }

}
