package au.com.dius.pactworkshop.consumer;

import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

public class Client {
    public JsonNode loadProviderJson() throws UnirestException {
        return Unirest.get("http://localhost:8080/flights")
                .queryString("flightDate", LocalDate.now().toString())
                .queryString("originAirport", "AMS")
                .queryString("destinationAirport", "BCN")
                .asJson().getBody();
    }

    public List<Object> fetchAndProcessData() throws UnirestException {
        JsonNode data = loadProviderJson();
        System.out.println("data=" + data);

        JSONObject jsonObject = data.getObject();
        int value = 100 / jsonObject.getInt("count");
        ZonedDateTime date = ZonedDateTime.parse(jsonObject.getString("date"));

        System.out.println("value=" + value);
        System.out.println("date=" + date);
        return Arrays.asList(value, date);
    }
}
