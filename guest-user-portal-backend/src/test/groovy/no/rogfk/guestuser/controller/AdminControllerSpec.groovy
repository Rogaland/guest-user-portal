package no.rogfk.guestuser.controller

import no.rogfk.guestuser.model.GuestUser
import no.rogfk.guestuser.service.GuestUserService
import no.rogfk.guestuser.service.HistoryService
import org.springframework.ldap.support.LdapNameBuilder
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AdminControllerSpec extends Specification {

    private AdminController controller
    private GuestUserService guestUserService
    private HistoryService historyService
    private MockMvc mockMvc

    void setup() {
        historyService = Mock(HistoryService)
        guestUserService = Mock(GuestUserService)
        controller = new AdminController(guestUserService: guestUserService, historyService: historyService)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    void cleanup() {
    }

    def "Get all guests"() {
        when:
        def response = mockMvc.perform(get("/api/admin/guest"))

        then:
        response.andExpect(status().isOk())
        1 * guestUserService.getAllGuests() >> [new GuestUser(dn: LdapNameBuilder.newInstance().build())]
    }


    def "Get guests for today"() {
        when:
        def response = mockMvc.perform(get("/api/admin/guest/today"))

        then:
        response.andExpect(status().isOk())
        1 * guestUserService.getTodaysGuests() >> [new GuestUser(dn: LdapNameBuilder.newInstance().build())]
    }

    def "Move expired guest users to history"() {
        when:
        def response = mockMvc.perform(post("/api/admin/guest/today"))

        then:
        response.andExpect(status().isOk())
        1 * historyService.historizeOldGuests()
    }

    def "Get all historical guest users"() {
        when:
        def response = mockMvc.perform(get("/api/admin/guest/historical"))

        then:
        response.andExpect(status().isOk())
        1 * guestUserService.getHistoricalGuests() >> [new GuestUser(dn: LdapNameBuilder.newInstance().build())]
    }

    def "Move all active guests users to history"() {
        when:
        def response = mockMvc.perform(post("/api/admin/guest/historical"))

        then:
        response.andExpect(status().isOk())
        1 * historyService.historizeAllGuests()
    }
}
