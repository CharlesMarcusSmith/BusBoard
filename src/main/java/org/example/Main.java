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
        // May need Query parameter at the end for app key or something?

        // Ask user for Post Code and take Stop Code as Input
        //http://api.postcodes.io/postcodes/
        // https://api.tfl.gov.uk/StopPoint/?lat=51.490231&lon=0.012856&stopTypes=NaptanOnstreetBusCoachStopPair&radius=100
        //"Get list of bus stops in a specified...
        //^ take lat, long, bus stop type V, and radius
        //NaptanOnstreetBusCoachStopPair ??

        // User input for Stop Code:
        String input = "";
        UserInputScanner userInputScanner = new UserInputScanner();
        input = userInputScanner.busStopCodeUserInput();

        // Making http request:
        RequestHandler requestHandler = new RequestHandler();
        String jsonResponse = requestHandler.BusStopInfo(input);

        // Converting the above JSON response to List of StopInfo objects:
        List<StopInfo> stops = new ArrayList<>();
        ResponseHandler responseHandler = new ResponseHandler();
        stops = responseHandler.BusStopInfo(jsonResponse);
        outputStops(stops);

        // User input for post code:
        input = userInputScanner.postCodeInput();
        //Last time we'll be using scanner:
        userInputScanners.close();

        //Making JSON request for Post Code:
        jsonResponse = requestHandler.postcode(input);

        //Converting response to lat and long stored in array
        String[] latAndLong = new String[2];
        responseHandler.LongAndLat(input);



    }
    public static void outputStops(List<StopInfo> stops){
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
}