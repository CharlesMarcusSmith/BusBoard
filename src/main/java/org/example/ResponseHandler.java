package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ResponseHandler {
    public List<StopInfo> BusStopInfo(String jsonString){
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

    public String[] LongAndLat(String response){
        JSONObject jsonObject = new JSONObject(response);
        String[] latAndLong = new String[2];

        latAndLong[0] = jsonObject.getJSONObject("Result").getString("latitude");
        latAndLong[1] = jsonObject.getJSONObject("Result").getString("longitude");

        return latAndLong;
    }

}
