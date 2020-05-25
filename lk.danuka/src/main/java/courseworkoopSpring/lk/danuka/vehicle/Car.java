package courseworkoopSpring.lk.danuka.vehicle;

import java.math.BigDecimal;
import java.sql.*;

public class Car extends Vehicle {

    private int numberOfAirbags;
    private String carColour;

    public Car( ){};

    public Car(int numberOfAirbags,String carColour,String plateNumber , String make, String model){
        super(plateNumber,make,model);
        this.numberOfAirbags=numberOfAirbags;

        this.carColour=carColour;
    }
    public Car(int numberOfAirbags, String carColour, String plateNumber, String make, BigDecimal rate, String model, String fuelType){
        super(plateNumber,make,rate,model,fuelType);
        this.numberOfAirbags=numberOfAirbags;

        this.carColour=carColour;
    }

    public int getNumberOfAirbags() {
        return numberOfAirbags;
    }

    public void setNumberOfAirbags(int numberOfAirbags) {
        this.numberOfAirbags = numberOfAirbags;
    }

    public String getCarColour() {
        return carColour;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }

    public String getType(){
        return "Car";
    }


    public void addVehicle() {
        //VehicleList.add(vehicle);



    }

    public String toString(){
        return "numberOfAirbags :"+numberOfAirbags+"  Car Colour  :"+carColour+" "+super.toString();
    }
}
