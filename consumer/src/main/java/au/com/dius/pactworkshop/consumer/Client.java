package au.com.dius.pactworkshop.consumer;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Client {
    private JsonNode loadProviderJson(String uri) throws UnirestException {
        return Unirest.get(uri + "/flights")
                .queryString("flightDate", LocalDate.now().toString())
                .queryString("originAirport", "AMS")
                .queryString("destinationAirport", "BCN")
                .asJson().getBody();
    }

    public List<Object> fetchAndProcessData(String uri) throws UnirestException {
        JsonNode data = loadProviderJson(uri);
        System.out.println("Retrieved data from provider: " + data);

        JSONObject jsonObject = data.getObject();
        LocalDate date = LocalDate.parse(jsonObject.getString("flightDate"));
        String originAirport = jsonObject.getString("originAirport");
        String destinationAirport = jsonObject.getString("destinationAirport");
        String airline = jsonObject.getString("airline");
        Double price = jsonObject.getDouble("price");
        String currency = jsonObject.getString("currency");

        System.out.println("Parsed date =" + date);
        return Arrays.asList(date, originAirport, destinationAirport, airline, price, currency);
    }
}
