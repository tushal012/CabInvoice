package com.bridgelabz;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabInvoiceTest {
    InvoiceGenerator invoiceGenerator =null;

    @Before
    public void setUp()  {
        invoiceGenerator = new InvoiceGenerator();
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
        Ride[] rides = { new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        double fare = invoiceGenerator.calculateFare(rides);
        Assert.assertEquals(30, fare, 0.0);
    }

    @Test
    public void givenMultipleRides_WhenCalculated_ReturnInvoiceSummery() {
        Ride[] rides = { new Ride(25.12, 40), new Ride(12.39, 25) };
        InvoiceSummary invoiceSummary = invoiceGenerator.getInvoiceSummary(rides);
        InvoiceSummary summary = new InvoiceSummary(2, 440.1);
        Assert.assertEquals(summary, invoiceSummary);
    }

}
