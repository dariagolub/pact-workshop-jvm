package au.com.dius.pactworkshop.springbootprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {

    @GetMapping("/flights")
    public Map<String, Serializable> getFlights(@RequestParam String flightDate,
                                                @RequestParam String originAirport,
                                                @RequestParam String destinationAirport) {
        Map<String, Serializable> map = new HashMap<>();
        map.put("flightDate", flightDate);
        map.put("originAirport", originAirport);
        map.put("destinationAirport", destinationAirport);
        map.put("airline", "Vueling");
        map.put("price", 49.95);
        map.put("currency", "EUR");
        return map;
    }

}
