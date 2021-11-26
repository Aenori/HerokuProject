package wcsdata.xmen.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.util.Pair;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest()
@ActiveProfiles("test")
@Transactional
@AutoConfigureMockMvc(addFilters = false)
public class AllGetTest {
    private int failureCount;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    private List<Pair<String, String>> showEndpointsAction() throws SQLException
    {
        return requestMappingHandlerMapping.getHandlerMethods().keySet().stream().map(t ->
                Pair.of(
                (t.getMethodsCondition().getMethods().size() == 0 ? "GET" : t.getMethodsCondition().getMethods().toArray()[0].toString()),
                        t.getPatternsCondition().getPatterns().toArray()[0].toString()
                )
        ).collect(Collectors.toList());
    }

    @Test
    public void testEndpoints() throws SQLException {
        resetFailure();

        List<Pair<String, String>> allEndPoints = showEndpointsAction();

        allEndPoints.stream().filter(
                p -> p.getFirst().equals("GET")
        ).forEach(p -> {
            try {
                testGetEndpoint(p.getSecond());
            } catch (Exception e) {
                addFailure();
                e.printStackTrace();
            }
        });

        assertEquals(0, failureCount);
    }

    private void addFailure() {
        ++failureCount;
    }

    private void resetFailure() {
        failureCount = 0;
    }

    private void testGetEndpoint(String path) throws Exception {
        if(!path.contains("{")) {
            if(path.equals("/error")) {
                mvc.perform(get(path).contentType(MediaType.TEXT_HTML))
                        .andExpect(status().is5xxServerError());
            }
            else if (path.startsWith("/api/")) {

            } else {
                mvc.perform(get(path).contentType(MediaType.TEXT_HTML))
                        .andExpect(status().isOk())
                        .andExpect(content()
                                .contentTypeCompatibleWith(MediaType.TEXT_HTML));
            }
        }
    }
}
