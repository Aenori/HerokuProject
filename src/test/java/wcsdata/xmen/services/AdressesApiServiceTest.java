package wcsdata.xmen.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClientRequestException;

import java.net.UnknownHostException;

import static org.junit.jupiter.api.Assertions.*;

class AdressesApiServiceTest {
    AdressesApiService adressesApiService = new AdressesApiService();

    @Test
    void getAdressAsJson() {
        JsonNode jsonNode = null;

        try {
            jsonNode = adressesApiService.getAdressAsJson("Paris");
        }
        catch(WebClientRequestException e) {
            // No connection internet, so failing this test is ok
            return;
        }

        assertEquals(5, jsonNode.get("features").size());
        JsonNode firstFeature = jsonNode.get("features").get(0);
        assertEquals("municipality",
                firstFeature.get("properties").get("type").asText()
        );
        assertEquals(2.347,
                firstFeature.get("geometry").get("coordinates").get(0).asDouble(),
                1e-6
        );
    }
}