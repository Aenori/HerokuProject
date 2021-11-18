package wcsdata.xmen.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class AdressesApiServiceTest {
    AdressesApiService adressesApiService = new AdressesApiService();

    @Test
    void getAdressAsJson() {
        JsonNode jsonNode = adressesApiService.getAdressAsJson("Paris");
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