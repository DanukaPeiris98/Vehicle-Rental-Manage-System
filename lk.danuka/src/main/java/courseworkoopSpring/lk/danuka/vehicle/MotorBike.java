package courseworkoopSpring.lk.danuka.vehicle;

import java.math.BigDecimal;


    public class MotorBike extends Vehicle {

        private String bikeType;
        private String startType;

        public MotorBike(){}

        public MotorBike(String bikeType, String startType, String plateNumber, String make, String model) {
            super(plateNumber, make, model);
            this.bikeType = bikeType;
            this.startType = startType;
        }

        public MotorBike(String bikeType, String startType, String plateNumber, String make, BigDecimal rate, String model, String type) {
            super(plateNumber, make, rate, model, type);
            this.bikeType = bikeType;
            this.startType = startType;
        }




        public String getBikeType() {
            return bikeType;
        }

        public void setBikeType(String bikeType) {
            this.bikeType = bikeType;
        }

        public String getStartType() {
            return startType;
        }

        public void setStartType(String startType) {
            this.startType = startType;
        }

        public String getType() {
            return "MotorBike";
        }

        public String toString() {
            return "  bikeType :" + bikeType + "  startType :" + startType + " " + super.toString();
        }
    }

