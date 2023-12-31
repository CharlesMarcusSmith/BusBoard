package org.example;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RequestHandler {
    public String postcode(String postcode) {
        HttpClient client = HttpClient.newHttpClient();
        String jsonResponse = "";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.postcodes.io/postcodes/" + postcode))
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonResponse = response.body();
        } catch(Exception e) {
            // System.out.println(e);
        }
        return jsonResponse;
    }

    public String busStopFinder(BigDecimal[] latAndLong) {
        HttpClient client = HttpClient.newHttpClient();
        String jsonResponse = "";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tfl.gov.uk/StopPoint/?lat=" + latAndLong[0] +  "&lon=" + latAndLong[1] +  "&stopTypes=NaptanOnstreetBusCoachStopPair"))
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonResponse = response.body();
        } catch(Exception e) {
            // System.out.println(e);
        }
        return jsonResponse;
    }

    public String busStopInfo(String stopCode) {
        HttpClient client = HttpClient.newHttpClient();
        String jsonResponse = "";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tfl.gov.uk/StopPoint/" + stopCode + "/Arrivals"))
                .build();
        try{
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            jsonResponse = response.body();
        } catch(Exception e) {
            // System.out.println(e);
        }
        return jsonResponse;
    }


}
