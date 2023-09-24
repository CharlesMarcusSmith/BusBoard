package org.example;

public class StopInfo {
    String id;
    String stationName;
    String destination;
    String expectedArrival;

    //Countdown:
    String timestamp;
    int timeToStation;

    public StopInfo(String id, String stationName, String destination, String expectedArrival, String timestamp, int timeToStation) {
        this.id = id;
        this.stationName = stationName;
        this.destination = destination;
        this.expectedArrival = expectedArrival;
        this.timestamp = timestamp;
        this.timeToStation = timeToStation;
    }

    public String getId() {
        return id;
    }

    public String getStationName() {
        return stationName;
    }

    public String detDestination(){
        return destination;
    }

    public String getExpectedArrival() {
        return expectedArrival;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getTimeToStation() {
        return timeToStation;
    }
}
