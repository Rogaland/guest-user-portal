package no.rogfk.guestuser.service

import spock.lang.Specification


class PasswordServiceSpec extends Specification {
    def passwordService

    void setup() {
        passwordService = new PasswordService()
    }

    def "Generate password"() {
        when:
        def password = passwordService.generatePassword()

        then:
        password.length() == 8
    }
}