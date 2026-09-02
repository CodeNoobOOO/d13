package sg.edu.nus.iss.d13revision.controllers;

import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DataController.class)
class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void healthCheckReturnsExpectedMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("HEALTH CHECK OK!"));
    }

    @Test
    void versionReturnsCurrentVersion() throws Exception {
        mockMvc.perform(get("/version"))
                .andExpect(status().isOk())
                .andExpect(content().string("The actual version is 1.0.0"));
    }

    @Test
    void nationsReturnsTenStructuredEntries() throws Exception {
        mockMvc.perform(get("/nations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(10))
                .andExpect(jsonPath("$[0].nationality").isString())
                .andExpect(jsonPath("$[0].nationality").value(not(emptyOrNullString())))
                .andExpect(jsonPath("$[0].capitalCity").isString())
                .andExpect(jsonPath("$[0].flag").isString())
                .andExpect(jsonPath("$[0].language").isString());
    }

    @Test
    void currenciesReturnsTwentyStructuredEntries() throws Exception {
        mockMvc.perform(get("/currencies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(20))
                .andExpect(jsonPath("$[0].name").isString())
                .andExpect(jsonPath("$[0].name").value(not(emptyOrNullString())))
                .andExpect(jsonPath("$[0].code").isString());
    }
}
