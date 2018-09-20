package au.com.dius.pactworkshop.consumer;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.time.LocalDate;

public class Consumer {

    public static void main(String[] args) throws UnirestException {
        System.out.println(new Client("http://localhost:8080").fetchAndProcessData(LocalDate.now()));
    }
}
