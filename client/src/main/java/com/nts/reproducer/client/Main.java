package com.nts.reproducer.client;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request =
                HttpRequest.newBuilder().timeout(Duration.of(5, ChronoUnit.MINUTES))
                        .uri(URI.create("http://localhost:8080/rest/reproducer/trigger")).GET().build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        System.out.println("Response was: %s %s".formatted(response.statusCode(), body.isBlank() ? "<No Body>" : body));
    }
}
