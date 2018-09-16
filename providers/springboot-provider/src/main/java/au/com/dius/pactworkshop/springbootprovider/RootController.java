package au.com.dius.pactworkshop.springbootprovider;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
public class RootController {

    @RequestMapping("/flights")
    public Map<String, Serializable> providerJson(@RequestParam String flightDate,
                                                  @RequestParam String originAirport,
                                                  @RequestParam String destinationAirport) {
        LocalDate date = LocalDate.parse(flightDate);
        Map<String, Serializable> map = new HashMap<>();
        map.put("test", "NO");
        map.put("flightDate", LocalDate.now().toString());
        map.put("originAirport", originAirport);
        map.put("destinationAirport", destinationAirport);
        map.put("airline", "Vueling");
        map.put("price", 49);
        map.put("currency", "EUR");
        return map;
    }

}
