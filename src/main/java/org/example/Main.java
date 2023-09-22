package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.json.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Your console application should take a bus stop code as an input, and print out the next 5 buses at that stop.
        // Remember to commit and push your changes as you go.

        //Take User Input for Stop Number --/
        //490000129R
        // Build HTTP client, whatever that means --/
        //Convert JSON String result to JSON Objects
        //Create a stopInfo or stopTime Class for storing info.
        // Print out next 5 busses at selected stop
        // May need Query parameter at the end for app key or something?

        // Ask user for Post Code and take Stop Code as Input

        String stopCode = "";
        UserInputScanner uis = new UserInputScanner();
        stopCode = uis.busStopCodeUserInput();

        BusStopRequestHandler();
    }

    public static void BusStopRequestHandler() {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tfl.gov.uk/StopPoint/490000129R/Arrivals"))
                .build();

        try{ HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            System.out.println(response.statusCode());
            System.out.println(response.body());

            BusStopResponseHandler(response.body());
        } catch(Exception e) {
//            System.out.println(e);
        }
    }

    public static void BusStopResponseHandler(String jsonString){
        // Just remember (while casting or using methods like getJSONObject and getJSONArray) that in JSON notation
        // [ … ] represents an array, so library will parse it to JSONArray
        // { … } represents an object, so library will parse it to JSONObject

        JSONArray jsonArray = new JSONArray(jsonString); //inside [ means its array, so this turns string to array

        for(int i=0; i<jsonArray.length(); i++){
            JSONObject jObject = jsonArray.getJSONObject(i); //looping through array and getting JSONobject { one at a time
            System.out.println(jObject.get("expectedArrival"));

            String id = jObject.get("id").toString();
            System.out.println(id);
        }





    }
}