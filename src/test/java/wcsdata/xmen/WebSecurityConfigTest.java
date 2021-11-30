package wcsdata.xmen;

import org.junit.jupiter.api.Test;
import wcsdata.xmen.config.WebSecurityConfig;

class WebSecurityConfigTest {
    WebSecurityConfig webSecurityConfig = new WebSecurityConfig();

    @Test
    void passwordEncoder() {
        //System.out.println(webSecurityConfig.passwordEncoder().encode("password"));
        assert(webSecurityConfig.passwordEncoder().matches(
                "password",
            webSecurityConfig.passwordEncoder().encode(
            "password")
        ));
    }
}