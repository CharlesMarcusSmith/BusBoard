package org.example;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.*;

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
        //Sort stop list
        // ^ require time to arrival as int.
        // Print out next 5 busses at selected stop
        // May need Query parameter at the end for app key or something?

        // Ask user for Post Code and take Stop Code as Input
        //"Get list of bus stops in a specified...
        //^ take lat, long, bus stop type V, and radius
        //NaptanOnstreetBusCoachStopPair ??

        //User Input for Stop Code:
        String stopCode = "";
        UserInputScanner uis = new UserInputScanner();
        stopCode = uis.busStopCodeUserInput();

        //Making http request:
        String jsonResponse = BusStopRequestHandler(stopCode);

        //Converting the above JSON response to List of StopInfo objects:
        List<StopInfo> stops = new ArrayList<>();
        stops = BusStopResponseHandler(jsonResponse);

        for(int i=0; i<5; i++) {
            StopInfo tempStopInfo = stops.get(i);

            int mins = tempStopInfo.getTimeToStation() / 60;
            int seconds = tempStopInfo.getTimeToStation() % 60;

            System.out.println(tempStopInfo.getStationName());
            System.out.println("Expected:" + tempStopInfo.expectedArrival);
            System.out.println(mins + "Mins " + seconds + "s");
            System.out.println();
        }

    }

    public static String BusStopRequestHandler(String UserStopCode) {
        HttpClient client = HttpClient.newHttpClient();
        String jsonResponse = "";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.tfl.gov.uk/StopPoint/" + UserStopCode + "/Arrivals"))
                .build();
        try{
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            jsonResponse = response.body();
        } catch(Exception e) {
//            System.out.println(e);
        }
        return jsonResponse;
    }

    public static List<StopInfo> BusStopResponseHandler(String jsonString){
        // Just remember (while casting or using methods like getJSONObject and getJSONArray) that in JSON notation
        // [ … ] represents an array, so library will parse it to JSONArray
        // { … } represents an object, so library will parse it to JSONObject

        JSONArray jsonArray = new JSONArray(jsonString); //inside [ means its array, so this turns string to array
        List<StopInfo> stops = new ArrayList<>();

        //Converting JSON String to array of StopInfo objects.
        for(int i=0; i<jsonArray.length(); i++) {
            JSONObject jObject = jsonArray.getJSONObject(i);
            //Variable to build StopInfo object:
            String id = jObject.get("id").toString();
            String stationName = jObject.get("stationName").toString();
            String expectedArrival = jObject.get("expectedArrival").toString();
            String timestamp = jObject.get("timestamp").toString();
            String tts = jObject.get("timeToStation").toString();
            int timeToStation = Integer.parseInt(tts);
            //Constructing StopInfo object:
            StopInfo si = new StopInfo(id, stationName, expectedArrival, timestamp, timeToStation);
            stops.add(si);
        }

        //Sorting List of stops by timeToArrival
        try {
            System.out.println("Trying to sort");
            Collections.sort(stops, new Comparator<StopInfo>() {
                @Override
                public int compare(StopInfo o1, StopInfo o2) {
                    int comp = o1.getTimeToStation() - o2.getTimeToStation();
                    return comp;
                }
            });
        } catch (Exception e) {
            System.out.println(e);
        }
        return stops;
    }


}