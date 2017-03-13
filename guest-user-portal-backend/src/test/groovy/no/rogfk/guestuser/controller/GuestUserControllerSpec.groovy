package no.rogfk.guestuser.controller

import no.rogfk.guestuser.model.GuestUserCreateStatus
import no.rogfk.guestuser.service.GuestUserService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


class GuestUserControllerSpec extends Specification {

    private GuestUserController controller
    private GuestUserService guestUserService
    private MockMvc mockMvc

    void setup() {
        guestUserService = Mock(GuestUserService)
        controller = new GuestUserController(guestUserService: guestUserService)

        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    def "Try to create existing guest user"() {
        when:
        def response = mockMvc.perform(post("/api/guest/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"mobile\": \"42\" }"))

        then:
        response.andExpect(status().isFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        1 * guestUserService.create(_  ,_ ,_  ) >>  null
    }

    def "Try to create new guest user"() {
        when:
        def response = mockMvc.perform(post("/api/guest/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"mobile\": \"42\" }"))

        then:
        response.andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
        1 * guestUserService.create(_  ,_ ,_  ) >> new GuestUserCreateStatus()
    }
}
