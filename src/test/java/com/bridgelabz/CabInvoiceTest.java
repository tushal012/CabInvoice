package com.bridgelabz;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabInvoiceTest {
    InvoiceGenerator invoiceGenerator = null;
    RideRepository rideRepository = null;
    Ride[] rides = null;

    @Before
    public void setUp()  {
        invoiceGenerator = new InvoiceGenerator();
        rideRepository = new RideRepository();
        invoiceGenerator.setRideRepository(rideRepository);
        rides = new Ride[]{new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.PREMIUM, 0.1, 1)
        };
    }


    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        double distance = 2.0;
        int time = 5;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceAndTime_shouldReturnMinFare() {
        double distance = 0.1;
        int time = 1;
        double fare = invoiceGenerator.calculateFare(distance, time);
        Assert.assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_shouldReturnTotalFare() {
        Ride[] rides = { new Ride(CabRide.NORMAL, 2.0, 5),
                new Ride(CabRide.NORMAL, 0.1, 1)
        };
        InvoiceSummary fare = invoiceGenerator.calculateFare(rides);
        Assert.assertEquals(String.valueOf(30), fare, 0.0);
    }

    @Test
    public void givenMultipleRides_WhenCalculated_ReturnInvoiceSummery(String ride) {
        Ride[] rides = { new Ride(CabRide.NORMAL, 25.12, 40), new Ride(CabRide.NORMAL, 12.39, 25) };
        InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary(ride);
        InvoiceSummary summary = new InvoiceSummary(2, 440.1);
        Assert.assertEquals(summary, invoiceSummary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
        String userId = "abc@abc.com";
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45.0);
        Assert.assertEquals(expectedInvoiceSummary, summary);
    }

}
