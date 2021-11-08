package wcsdata.xmen;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;

class WebSecurityConfigTest {
    WebSecurityConfig webSecurityConfig = new WebSecurityConfig();

    @Test
    void passwordEncoder() {
        assert(webSecurityConfig.passwordEncoder().matches(
                "password",
            webSecurityConfig.passwordEncoder().encode(
            "password")
        ));
    }
}