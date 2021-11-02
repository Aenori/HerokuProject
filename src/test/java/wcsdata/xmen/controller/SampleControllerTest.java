package wcsdata.xmen.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
@Transactional
@AutoConfigureWebTestClient
class SampleControllerTest {
    @Autowired
    private WebTestClient webClient;

    @Test
    void index() {
        this.webClient.get().uri("/").exchange().expectStatus().isOk()
                .expectBody(String.class);
    }

    @Test
    void getAllCerebookUser() {
    }

    @Test
    void getAllCerebookPosts() {
    }

    @Test
    void db() {
    }
}