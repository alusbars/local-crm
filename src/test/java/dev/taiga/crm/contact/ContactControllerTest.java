package dev.taiga.crm.contact;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;

@WebMvcTest(ContactController.class)
class ContactControllerTest {
    @Autowired
    MockMvc mvc;
    @MockitoBean
    ContactService service;

    @Test
    void contactsRouteCanFilterByStage() throws Exception {
        given(service.list("ACTIVE", null)).willReturn(List.of());
        mvc.perform(get("/crm/contacts").param("stage", "ACTIVE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }
}
