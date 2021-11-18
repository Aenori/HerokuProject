package wcsdata.xmen.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class AdressesApiService {
    private static final String ADRESSE_API_URL = "https://api-adresse.data.gouv.fr/search";

    private WebClient webClient;

    // This is a lazy initializer, it is a very classical pattern in Java
    private WebClient getWebClient() {
        if(webClient == null) {
            webClient = WebClient.create(ADRESSE_API_URL);
        }

        return webClient;
    }

    public JsonNode getAdressAsJson(String value) {
        try {
            return new ObjectMapper().readTree(getAdressAsString(value));
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    private String getAdressAsString(String value) {
        return getWebClient().get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("q", "paris")
                        .build())
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
