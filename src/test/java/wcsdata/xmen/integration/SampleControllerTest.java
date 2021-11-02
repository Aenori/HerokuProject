package wcsdata.xmen.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest()
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc
class SampleControllerTest {
    @Autowired
    private MockMvc mvc;

    @Test
    void index() {
    }

    @Test
    void getAllCerebookUser() throws Exception {
        mvc.perform(get("/cerebookUsers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name", is("Wolverine")));
    }

    @Test
    void getAllCerebookPosts() {
    }

    @Test
    void db() {
    }
}