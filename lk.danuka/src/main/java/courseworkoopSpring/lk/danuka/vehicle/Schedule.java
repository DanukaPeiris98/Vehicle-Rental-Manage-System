package courseworkoopSpring.lk.danuka.vehicle;

import java.sql.Date;

public class Schedule {
    private String plateNumber;
    private Date date;
    private String customerId;

    public Schedule(String plateNumber, Date date) {
        this.plateNumber = plateNumber;
        this.date = date;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
