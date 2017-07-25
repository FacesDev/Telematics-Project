package com.company;

/**
 * Created by solderedmachd on 7/21/17.
 */
public class VehicleInfo {
    public int VIN;
    public double odometer;
    public double consumption;
    public double odometerReading;
    public double engineSize;
    public double milesPerGallon;

    public VehicleInfo() {

    }

    public int getVIN() {
        return VIN;
    }

    public void setVIN(int VIN) {
        this.VIN = VIN;
    }

    public double getOdometer() {
        return odometer;
    }

    public void setOdometer(double odometer) {
        this.odometer = odometer;
    }

    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    public double getOdometerReading() {
        return odometerReading;
    }

    public void setOdometerReading(double odometerReading) {
        this.odometerReading = odometerReading;
    }

    public double getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(double engineSize) {
        this.engineSize = engineSize;
    }

    public double getMilesPerGallon(double odometer, double consumption){
        return odometer/consumption; }


//    @JsonIgnore
//    public double setMilesPerGallon(double milesPerGallon) {
//
//        this.milesPerGallon = milesPerGallon;
//    }

}
