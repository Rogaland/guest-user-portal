package no.rogfk.guestuser.controller

import no.rogfk.guestuser.model.EmployeeSearch
import no.rogfk.guestuser.service.ConfigService
import no.rogfk.guestuser.service.EmployeeSearchService
import org.springframework.ldap.support.LdapNameBuilder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class EmployeeControllerSpec extends Specification {

    private EmployeeController controller
    private ConfigService configService
    private EmployeeSearchService employeeSearchService
    private MockMvc mockMvc

    void setup() {
        configService = Mock(ConfigService){
            getMinQueryLength() >> 3
        }
        employeeSearchService = Mock(EmployeeSearchService)
        controller = new EmployeeController(configService: configService,
                employeeSearchService: employeeSearchService)

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    def "Search for employee with valid parameter"() {
        when:
        def response = mockMvc.perform(get("/api/employee?q=1234"))

        then:
        response.andExpect(status().isOk())
        1 * employeeSearchService.search(_ as String) >> [new EmployeeSearch(dn: LdapNameBuilder.newInstance().build())]
        1 * configService.getMinQueryLength()

    }

    def "Search for employee with too short parameter"() {
        when:
        def response = mockMvc.perform(get("/api/employee?q=1"))

        then:
        response.andExpect(status().isBadRequest())
    }

    def "Search for employee without parameter"() {
        when:
        def response = mockMvc.perform(get("/api/employee"))

        then:
        response.andExpect(status().isBadRequest())
    }

}
