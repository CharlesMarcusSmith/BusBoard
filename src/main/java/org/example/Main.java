package org.example;

import java.math.BigDecimal;
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

        // DEVELOPEMENT NOTES / TO DO:
        // May need Query parameter at the end for app key or something?
        // Ask user for Post Code and take Stop Code as Input
        //"Get list of bus stops in a specified...
        //^ take lat, long, bus stop type V, and radius
        //NaptanOnstreetBusCoachStopPair ??
        //Reduce size of results to two till sorted by Distance
        // Exception hadnling and loggin required.s
        //What if no results are found but data is entered correctly? like no bus stops are found with long lat etc..

        String input = "";
        UserInputScanner userInputScanner = new UserInputScanner();
        input = userInputScanner.inputOptionSelector();

        if(input.equalsIgnoreCase("Postcode") || input.equalsIgnoreCase("Stopcode")){
            if(input.equalsIgnoreCase("Postcode")){
                postcodeOptionSelected();
            }
            if(input.equalsIgnoreCase("Stopcode")){
                busStopOptionSelected();
            }
        }
        else{
            System.out.println("You have not entered a correct selection");
        }
        userInputScanner.close();
    }

    public static void postcodeOptionSelected(){
        // User input for post code:
        UserInputScanner userInputScanner = new UserInputScanner();
        String input = userInputScanner.postCodeInput();

        //Making JSON request for Post Code:
        RequestHandler requestHandler = new RequestHandler();
        String jsonResponse = requestHandler.postcode(input);

        //Converting response to lat and long stored in array
        ResponseHandler responseHandler = new ResponseHandler();
        BigDecimal[] latAndLong = responseHandler.LatAndLong(jsonResponse);

        jsonResponse = requestHandler.busStopFinder(latAndLong);
        List<String> stops = responseHandler.stopFinder(jsonResponse);
    }

    public static void busStopOptionSelected(){
        // User input for Stop Code:
        String input = "";
        UserInputScanner userInputScanner = new UserInputScanner();
        input = userInputScanner.busStopCodeUserInput();

        // Making http request:
        RequestHandler requestHandler = new RequestHandler();
        String jsonResponse = requestHandler.busStopInfo(input);

        // Converting the above JSON response to List of StopInfo objects:
        List<StopInfo> stops = new ArrayList<>();
        ResponseHandler responseHandler = new ResponseHandler();
        stops = responseHandler.BusStopInfo(jsonResponse);
        outputStops(stops);
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