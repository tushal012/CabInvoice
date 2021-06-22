package com.bridgelabz;

import java.util.ArrayList;

class Ride extends ArrayList<Ride> {
    public RideType rideType;
    public  double distance;
    public  double time;

    public enum RideType{
        Normal,Premium
    }
    public Ride(double distance, double time, RideType rideType) {
        this.rideType=rideType;
        this.distance=distance;
        this.time=time;
        }

}
