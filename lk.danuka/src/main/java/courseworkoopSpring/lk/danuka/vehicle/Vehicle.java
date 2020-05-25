package courseworkoopSpring.lk.danuka.vehicle;
import java.math.BigDecimal;

public abstract class Vehicle {


    private String plateNumber;
    private  String make;
    private BigDecimal rate;
    private String model;
    private String fuelType;


    public Vehicle( ){};

    public Vehicle(String plateNumber,String make,String model){
        this.plateNumber=plateNumber;

        this.make=make;
        this.model=model;
    };

    public Vehicle(String plateNumber,String make,BigDecimal rate,String model,String fuelType){

        this.plateNumber=plateNumber;
        this.make=make;
        this.rate=rate;
        this.model=model;
        this.fuelType=fuelType;

    }


    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String type) {
        this.fuelType = type;
    }


    public String toString(){
        return " plate number :"+plateNumber+"  make :"+make+"  model  :"+model;
    }


}

