package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner scannerInput = new Scanner(System.in);

        System.out.println( "Enter Your Car's Information as Prompted Below");
        VehicleInfo car1 = new VehicleInfo();

        System.out.println( "What is your Engine Size?");
        Double inputEngine = scannerInput.nextDouble();
        car1.setEngineSize(inputEngine);

        System.out.println( "What is your VIN?");
        int inputVIN = scannerInput.nextInt();
        car1.setVIN(inputVIN);

        System.out.println( "What does your Odometer read?");
        Double inputOdometer = scannerInput.nextDouble();
        car1.setOdometer(inputOdometer);

        System.out.println( "What is your Gas Consumption?");
        Double inputConsumption = scannerInput.nextDouble();
        car1.setConsumption(inputConsumption);

        System.out.println( "What is your Odometer reading for last oil change? ");
        Double inputOdometerReading = scannerInput.nextDouble();
        car1.setOdometerReading(inputOdometerReading);



//        car1.milesPerGallon = inputOdometer / inputConsumption;
//        car1.setMilesPerGallon(car1.milesPerGallon);

        TelematicService.report(car1);

    }
}
