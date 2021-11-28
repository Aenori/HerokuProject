package wcsdata.xmen.external;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.BaseJsonNode;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
public class JacksonSerialisationTest {
    private ObjectMapper objectMapper = new ObjectMapper();

    private Person getSuperman() {
        return new Person(1, "Clark Kent");
    }

    private JsonNode getSupermanAsJson() {
        return objectMapper.valueToTree(getSuperman());
    }

    @Test
    public void serialisationObjectToStringTest() throws JsonProcessingException {
        String myObjectAsJson = null; // Change this line
        assertEquals("{\"id\":1,\"name\":\"Clark Kent\"}", myObjectAsJson);
    }

    @Test
    public void serialisationObjectToJsonNodeTest() {
        // It is forbidden to use getSupermanAsJson() here !!
        JsonNode myJson = null; // Change this line
        assertEquals(getSupermanAsJson(), myJson);
    }

    @Test
    public void deserialisationStringToObject() throws JsonProcessingException {
        String supermanAsString = "{\"id\":1,\"name\":\"Clark Kent\"}";
        // It is forbidden to use getSuperman() here !!
        Person superman = null; // Change this line
        assertEquals(getSuperman(), superman);
    }

    @Test
    public void deserialisationStringToJsonNode() throws JsonProcessingException {
        String supermanAsString = "{\"id\":1,\"name\":\"Clark Kent\"}";
        // It is forbidden to use getSuperman() here !!
        JsonNode superman = null; // Change this line
        assertEquals(getSupermanAsJson(), superman);
    }
}
