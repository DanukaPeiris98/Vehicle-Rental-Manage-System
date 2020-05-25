package courseworkoopSpring.lk.danuka.vehicle;

import courseworkoopSpring.lk.danuka.DBConnector.DatabaseConnecter;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.sql.Date;
import java.util.*;

@Service
public class VehicleService {
//Get cars from database
    public List<Car> getCarsDB() {
        List<Car> cars = new ArrayList<Car>();
        String sqlCode = "select  * from  car";
        ResultSet result;
        try {
            Connection conn = DatabaseConnecter.connectToDatabase();
            PreparedStatement stm = conn.prepareStatement(sqlCode);
            result = stm.executeQuery();//execute code
            while (result.next()) {
                Car car = new Car();
                car.setPlateNumber(result.getString(1));
                car.setMake(result.getString(2));
                car.setRate(result.getBigDecimal(3));
                car.setModel(result.getString(4));
                car.setFuelType(result.getString(5));
                car.setNumberOfAirbags(result.getInt(6));
                car.setCarColour(result.getString(7));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return cars;
    }

//get Bikes from database
    public List<MotorBike> getMotorBikeDB() {
        List<MotorBike> bikes = new ArrayList<MotorBike>();
        String sqlCode = "select  * from  motor_bike";
        ResultSet result;
        try {
            Connection conn = DatabaseConnecter.connectToDatabase();
            PreparedStatement stm = conn.prepareStatement(sqlCode);
            result = stm.executeQuery();//execute code
            while (result.next()) {
                MotorBike bike = new MotorBike();
                bike.setPlateNumber(result.getString(1));
                bike.setMake(result.getString(2));
                bike.setRate(result.getBigDecimal(3));
                bike.setModel(result.getString(4));
                bike.setFuelType(result.getString(5));
                bike.setBikeType(result.getString(6));
                bike.setStartType(result.getString(7));
                bikes.add(bike);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return bikes;
    }
//filtering cars by make
    public List<Car> getCarsMakeDB(String make) {
        List<Car> cars = new ArrayList<Car>();
        String sqlCode = "select  * from  car WHERE make=?";

        try {
            Connection conn = DatabaseConnecter.connectToDatabase();
            PreparedStatement preparedStmt = conn.prepareStatement(sqlCode);
            preparedStmt.setString(1, make);
            ResultSet result;


            result = preparedStmt.executeQuery();//execute code
            while (result.next()) {
                Car car = new Car();
                car.setPlateNumber(result.getString(1));
                car.setMake(result.getString(2));
                car.setRate(result.getBigDecimal(3));
                car.setModel(result.getString(4));
                car.setFuelType(result.getString(5));
                car.setNumberOfAirbags(result.getInt(6));
                car.setCarColour(result.getString(7));
                cars.add(car);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return cars;
    }
//filtering Bikes by make
    public List<MotorBike> getBikesMakeDB(String make) {
        List<MotorBike> bikes = new ArrayList<MotorBike>();
        String sqlCode = "select  * from  motor_bike WHERE make=?";

        try {
            Connection conn = DatabaseConnecter.connectToDatabase();
            PreparedStatement preparedStmt = conn.prepareStatement(sqlCode);
            preparedStmt.setString(1, make);
            ResultSet result;


            result = preparedStmt.executeQuery();//execute code
            while (result.next()) {
                MotorBike bike = new MotorBike();
                bike.setPlateNumber(result.getString(1));
                bike.setMake(result.getString(2));
                bike.setRate(result.getBigDecimal(3));
                bike.setModel(result.getString(4));
                bike.setFuelType(result.getString(5));
                bike.setBikeType(result.getString(6));
                bike.setStartType(result.getString(7));
                bikes.add(bike);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return bikes;
    }
//To get plate number and date for check available
    public Map<String, String> getScheduleDB(String plateNumber, Date pickup, Date drop) {
        String sqlCode = "select * from  schedule WHERE plateNumber=? AND date =?";
            List<Date> dates= getDatesBetween(pickup, drop);
         try {
            Connection conn = DatabaseConnecter.connectToDatabase();
            PreparedStatement preparedStmt = conn.prepareStatement(sqlCode);
            preparedStmt.setString(1, plateNumber);
             for (Date date:
                  dates) {
                 preparedStmt.setDate(2, date);
                 ResultSet result;
                 result = preparedStmt.executeQuery();//execute code
                 if(result.next()){
                     return Collections.singletonMap("status", "unavailable");
                 }
             }
             return Collections.singletonMap("status", "available");


        } catch (SQLException e) {
            e.printStackTrace();
return null;

        }
    }
//insert plate number and date to schedule db
    public Map<String, String> setScheduleDB(String plateNumber, Date pickup, Date drop) {
        String sqlCode = "INSERT into schedule (plateNumber,date) values (?,?)";
        List<Date> dates= getDatesBetween(pickup, drop);
        try {
            Connection conn = DatabaseConnecter.connectToDatabase();
            PreparedStatement preparedStmt = conn.prepareStatement(sqlCode, Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1, plateNumber);
            for (Date date:
                 dates) {
                preparedStmt.setDate(2, date);
                preparedStmt.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        }
        return Collections.singletonMap("status", "success");
    }
//get make types of cars for filter
    public List<String> getCarMakeDB() {
        String sqlCode="select DISTINCT make FROM car";
        List<String> cMake= new ArrayList<String>();
        Connection conn = DatabaseConnecter.connectToDatabase();
        try {
            PreparedStatement preparedStmt = conn.prepareStatement(sqlCode);
            ResultSet result;
            result = preparedStmt.executeQuery();//execute code
            while (result.next()) {
                cMake.add(result.getString(1));

            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return cMake;
    }

//get make types from bikes for filter
    public List<String> getBikeMakeDB() {
        String sqlCode="select DISTINCT make FROM motor_bike";
        List<String> bMake = new ArrayList<String>();
        Connection conn = DatabaseConnecter.connectToDatabase();
        try {

            PreparedStatement preparedStmt = conn.prepareStatement(sqlCode);

            ResultSet result;


            result = preparedStmt.executeQuery();//execute code
            while (result.next()) {

                bMake.add(result.getString(1));


            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return bMake;
    }
//get between dates
    public  List<Date> getDatesBetween(
            Date startDate, Date endDate) {

        List<Date> datesInRange = new ArrayList<>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        Calendar endCalendar = new GregorianCalendar();
        endCalendar.setTime(endDate);

        while (calendar.before(endCalendar)) {
            java.util.Date result = calendar.getTime();
            java.sql.Date sqlDate = new java.sql.Date(result.getTime());
            datesInRange.add(sqlDate);
            calendar.add(Calendar.DATE, 1);
        }

        java.sql.Date sqlDate = new java.sql.Date(endDate.getTime());
        datesInRange.add(sqlDate);
        calendar.add(Calendar.DATE, 1);

        return datesInRange;
    }
}



