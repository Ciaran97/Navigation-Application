package com.example.swannavigation.ui.home;

import com.example.swannavigation.RouteEntry;

public class Trip {

    public String TripDate;
    public RouteEntry Origin = new RouteEntry();
    public RouteEntry Destination = new RouteEntry();

    public Trip(String TripDate, Double OriginLat, Double OriginLng, Double DestinationLat, Double DestinationLng) {
        this.TripDate = TripDate;
        this.Origin.Latitude = OriginLat;
        this.Origin.Longitude = OriginLng;
        this.Destination.Latitude = DestinationLat;
        this.Destination.Longitude = DestinationLng;
    }

    public Trip() {

    }
}
