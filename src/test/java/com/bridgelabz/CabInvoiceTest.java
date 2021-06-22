package com.bridgelabz;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CabInvoiceTest {
    InvoiceGenerator invoiceGenerator = null;

    @Before
    public void setUp() throws Exception {
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    public void givenDistanceAndTime_ShouldReturnTotalFare() {
        InvoiceGenerator invoiceService = new InvoiceGenerator();
        double distance = 2.0;
        int time = 5;
        double fare = invoiceService.calculateFare(distance,time, Ride.RideType.Normal);
        assertEquals(25, fare, 0.0);
    }

    @Test
    public void givenLessDistanceOrTime_ShouldReturnMinFare() {
        InvoiceGenerator invoiceService = new InvoiceGenerator();
        double distance = 0.1;
        int time = 1;
        double fare = invoiceService.calculateFare(distance,time, Ride.RideType.Normal);
        assertEquals(5, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_ShouldReturnInvoiceSummary() {
        InvoiceGenerator invoiceService = new InvoiceGenerator();
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        InvoiceSummary summary = invoiceService.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenUserIdAndRides_ShouldReturnInvoiceSummary() throws CabInvoiceException {
        String userId = "a@b.com";
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenInputWithoutUserId_WhenCalculated_ShouldReturnCustomException()  {
        String userId = null;
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        try {
            invoiceGenerator.addRides(userId, rides);
            InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        } catch (CabInvoiceException exception) {
            assertEquals("userId cant be null", exception.getMessage());
        }
    }
    @Test
    public void givenMultipleRidesArrays_ShouldReturnInvoiceSummary() throws CabInvoiceException{
        String userId = "amit.com";
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        invoiceGenerator.addRides(userId, rides);
        Ride[] rides1 = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        invoiceGenerator.addRides(userId, rides1);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 60);
        assertEquals(expectedInvoiceSummary, summary);
    }

    @Test
    public void givenMultipleUserIdAndRides_ShouldReturnInvoiceSummary() throws CabInvoiceException {
        String userId = "amit.com";
        String userId1="amy.com";
        Ride[] rides = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        invoiceGenerator.addRides(userId, rides);
        Ride[] rides1 = {
                new Ride(2.0, 5, Ride.RideType.Normal),
                new Ride(0.1, 1, Ride.RideType.Normal)
        };
        invoiceGenerator.addRides(userId, rides1);
        Ride[] rides2 = {
                new Ride(3.0, 5, Ride.RideType.Normal),
                new Ride(0.2, 1, Ride.RideType.Normal)
        };
        invoiceGenerator.addRides(userId1,rides2);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 60);
        assertEquals(expectedInvoiceSummary, summary);
    }
    @Test
    public void givenPremiumAndNormalRides_ShouldReturnInvoiceSummary() throws CabInvoiceException{
        String userId = "tushal.com";
        String userId1="tush.com";
        Ride[] rides = {
                new Ride(2.0,5.0, Ride.RideType.Normal),
                new Ride(0.1,1.0, Ride.RideType.Premium)
        };
        invoiceGenerator.addRides(userId, rides);
        InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 45);
        assertEquals(expectedInvoiceSummary, summary);
    }

}
