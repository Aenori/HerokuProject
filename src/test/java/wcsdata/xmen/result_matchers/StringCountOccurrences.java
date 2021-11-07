package wcsdata.xmen.result_matchers;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.util.StringUtils;
import static org.junit.jupiter.api.Assertions.*;

public class StringCountOccurrences implements ResultMatcher {
    private final int nb;
    private final String s;

    public StringCountOccurrences(String s, int nb) {
        this.s = s;
        this.nb = nb;
    }

    @Override
    public void match(MvcResult result) throws Exception {
        assertEquals(this.nb,
                StringUtils.countOccurrencesOf(
                result.getResponse().getContentAsString(),
                this.s));
    }
}
