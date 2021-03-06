package no.rogfk.guestuser.service

import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.query.LdapQuery
import spock.lang.Specification

class EmployeeServiceSpec extends Specification {

    def "Search for employee"() {
        given:
        def ldapTemplate = Mock(LdapTemplate)
        def employeeService = new EmployeeSearchService(ldapTemplate: ldapTemplate)

        when:
        employeeService.search("bladi")

        then:
        1 * ldapTemplate.find(_ as LdapQuery, _ as Class)
    }

    def "Convert to like string"() {
        given:
        def employeeService = new EmployeeSearchService()
        def s = "Ola Flytt"

        when:
        def r = employeeService.toLikeString(s)

        then:
        r == "*Ola*Flytt*"
    }
}
