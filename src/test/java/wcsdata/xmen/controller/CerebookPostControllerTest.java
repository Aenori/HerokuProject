package wcsdata.xmen.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest()
@ActiveProfiles("test")
@Transactional
class CerebookPostControllerTest {
    @Autowired
    CerebookPostController cerebookPostController;

    @Test
    void getControllerRoute() {
        assertEquals("posts", cerebookPostController.getControllerRoute());
    }
}