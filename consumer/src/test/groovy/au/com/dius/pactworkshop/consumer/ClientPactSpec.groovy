package au.com.dius.pactworkshop.consumer

import au.com.dius.pact.consumer.PactVerificationResult
import au.com.dius.pact.consumer.groovy.PactBuilder
import spock.lang.Specification
import java.time.LocalDate

class ClientPactSpec extends Specification {

    private Client client
    private LocalDate date
    private String originAirport
    private String destinationAirport
    private PactBuilder provider

    def setup() {
        client = new Client('http://localhost:1234')
        date = LocalDate.now()
        originAirport = 'AMS'
        destinationAirport = 'BCN'
        provider = new PactBuilder()
        provider {
            serviceConsumer 'Flight Consumer'
            hasPactWith 'Flight Provider'
            port 1234
        }
    }

    def 'Pact with flight provider'() {
        given:
        def json = [
                flightDate : date.toString(),
                originAirport: originAirport,
                destinationAirport: destinationAirport,
                airline: 'Vueling',
                price: 49.95,
                currency: 'EUR'
        ]
        provider {
            given('flights count > 0')
            uponReceiving('a request for flight data')
            withAttributes(path: '/flights', query: [flightDate: date.toString(), originAirport: originAirport, destinationAirport: destinationAirport])
            willRespondWith(status: 200, body: JsonOutput.toJson(json), headers: ['Content-Type': 'application/json'])
        }

        when:
        def result
        PactVerificationResult pactResult = provider.runTest {
            result = client.fetchAndProcessData(date)
        }

        then:
        pactResult == PactVerificationResult.Ok.INSTANCE
        result[4] == json.price
    }

}
