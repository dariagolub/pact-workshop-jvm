package au.com.dius.pactworkshop.consumer;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Client {

    private final String url;

    public Client(String url) {
        this.url = url;
    }

    private JsonNode loadProviderJson(LocalDate flightDate) throws UnirestException {
        return Unirest.get(url + "/flights")
                .queryString("flightDate", flightDate.toString())
                .queryString("originAirport", "AMS")
                .queryString("destinationAirport", "BCN")
                .asJson().getBody();
    }

    public List<Object> fetchAndProcessData(LocalDate localDate) throws UnirestException {
        JsonNode data = loadProviderJson(localDate);
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
