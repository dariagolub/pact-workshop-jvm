package au.com.dius.pactworkshop.consumer;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.LocalDate;

public class Client {
    public JsonNode loadProviderJson() throws UnirestException {
        return Unirest.get("http://localhost:8080/flights")
                .queryString("flightDate", LocalDate.now().toString())
                .queryString("originAirport", "AMS")
                .queryString("destinationAirport", "BCN")
                .asJson().getBody();
    }
}
