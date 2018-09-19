package au.com.dius.pactworkshop.consumer;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.junit.Rule;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class ClientTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

    @Test
    public void canProcessTheJsonPayloadFromTheProvider() throws UnirestException {

        String date = "2018-09-20";
        Double price = 29.25;

        stubFor(get(urlPathEqualTo("/flights"))
                .withQueryParam("flightDate", matching(".+"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"test\": \"NO\", \"flightDate\": \"" + date + "\", " +
                                "\"originAirport\": \"AMS\", " +
                                "\"destinationAirport\": \"BCN\", " +
                                "\"airline\": \"Vueling\", " +
                                "\"price\": \"" + price + "\", " +
                                "\"currency\": \"EUR\"}")));

        List<Object> data = new Client("http://localhost:8089").fetchAndProcessData(LocalDate.parse(date));

        assertThat(data, hasSize(6));
        assertThat(data.get(0), is(LocalDate.parse(date)));
        assertThat(data.get(4), is(price));
    }

}