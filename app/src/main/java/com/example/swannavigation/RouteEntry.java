package com.example.swannavigation;


        import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class RouteEntry {

    public Double Latitude;
    public Double Longitude;


    public RouteEntry() {

    }

    public RouteEntry(Double Longitude, Double Latitude) {
        this.Latitude = Latitude;
        this.Longitude = Longitude;
    }

}
