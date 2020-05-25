import java.math.BigDecimal;

public class Bus extends Vehicle {

    private String busType;
    private String doorType;

    public Bus(String busType,String doorType,String plateNumber , String make, String model){
        super(plateNumber,make,model);
        this.busType=busType;
        this.doorType=doorType;
    }
    public Bus(String busType, String doorType,String plateNumber, String make, BigDecimal rate, String model, String type){
        super(plateNumber,make,rate,model,type);
        this.busType=busType;
        this.doorType=doorType;
    }

    public String getBusType() {
        return busType;
    }

    public void setBusType(String busType) {
        this.busType = busType;
    }

    public String getDoorType() {
        return doorType;
    }

    public void setDoorType(String doorType) {
        this.doorType = doorType;
    }

    public String getType(){
        return "Bus";
    }

    public String toString() {
        return "  busType :" + busType + "  doorType :" + doorType + " " + super.toString();
    }
}
