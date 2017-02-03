package test.groovy.no.rogfk.guestuser.service

import main.java.no.rogfk.guestuser.model.Employee
import org.springframework.ldap.core.LdapTemplate
import org.springframework.ldap.query.LdapQuery
import org.springframework.ldap.query.LdapQueryBuilder
import spock.lang.Specification


class EmployeeServiceSpec extends Specification {

    def "Search for employee"() {
        given:
        def ldapTemplate = Mock(LdapTemplate)
        def employeeService = new EmployeeService(ldapTemplate: ldapTemplate)

        when:
        employeeService.search("bladi")

        then:
        1 * ldapTemplate.find(_ as LdapQuery, _ as Class)
    }

    def "Convert to like string"() {
        given:
        def employeeService = new EmployeeService()
        def s = "Ola Flytt"

        when:
        def r = employeeService.toLikeString(s)

        then:
        r == "*Ola*Flytt*"
    }
}
