
import java.io.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WestminsterRentalVehicleManager implements RentalVehicleManager {
        public WestminsterRentalVehicleManager() {

    }

    @Override
    public void addVehicle(Vehicle vehicle) throws SQLException {

            if (vehicle instanceof Car) {
                String sqlCode = "INSERT into car (plateNumber,make, rate, model, fuelType,numberOfAirbags,carColour) " +
                        "VALUES (?,?,?,?,?,?,?)";//sql code to add Car to database

                try {
                     Connection conn = DatabaseConnecter.connectToDatabase();
                    PreparedStatement stm = conn.prepareStatement(sqlCode, Statement.RETURN_GENERATED_KEYS);
                    stm.setString(1, vehicle.getPlateNumber());//pushing values to the statement
                    stm.setString(2, vehicle.getMake());
                    stm.setBigDecimal(3, vehicle.getRate());
                    stm.setString(4, vehicle.getModel());
                    stm.setString(5, vehicle.getFuelType());
                    stm.setInt(6, ((Car) vehicle).getNumberOfAirbags());
                    stm.setString(7, ((Car) vehicle).getCarColour());
                    stm.executeUpdate();//execute sql code

                } catch (SQLIntegrityConstraintViolationException s) {
                    System.out.println("Already exist");//if primary key duplicates user already exist

                } catch (SQLException e) {
                    System.err.println(e);

                }

            }
            if (vehicle instanceof MotorBike) {
                String sqlCode = "INSERT into motor_bike (plateNumber,make, rate, model, fuelType,bikeType,startType) " +
                        "VALUES (?,?,?,?,?,?,?)";//sql code to add MotorBike to database

                try {
                    Connection conn = DatabaseConnecter.connectToDatabase();
                    PreparedStatement stm = conn.prepareStatement(sqlCode, Statement.RETURN_GENERATED_KEYS);
                    stm.setString(1, vehicle.getPlateNumber());//pushing values to the statement
                    stm.setString(2, vehicle.getMake());
                    stm.setBigDecimal(3, vehicle.getRate());
                    stm.setString(4, vehicle.getModel());
                    stm.setString(5, vehicle.getFuelType());
                    stm.setString(6, ((MotorBike) vehicle).getBikeType());
                    stm.setString(7, ((MotorBike) vehicle).getStartType());
                    stm.executeUpdate();//execute sql code

                } catch (SQLIntegrityConstraintViolationException s) {
                    System.out.println("Already exist");//if primary key duplicates user already exist

                } catch (SQLException e) {
                    System.err.println(e);

                }


        }

    }

    @Override
   public void deleteVehicle(int vNum,String number) {
       //VehicleList.remove(number);

       if (vNum == 1) {
           try {
               Connection conn = DatabaseConnecter.connectToDatabase();
               String query = "delete from car where plateNumber = ?";
               PreparedStatement preparedStmt = conn.prepareStatement(query);
               preparedStmt.setString(1, number);
               // execute the preparedstatement
               preparedStmt.execute();

               //To check available slots
               String query2 = "select (select COUNT(*) from car) AS count1, ( select COUNT(*) from motor_bike) AS count2 FROM dual";
               Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(query2);
               rs.next();
               int numOfCars = rs.getInt(1);
               int numOfMB = rs.getInt(2);
               System.out.println("Available Parking Lots for Cars "+(50-numOfCars-numOfMB));

               conn.close();
               System.out.println("Car "+number+" deleted");

           } catch (Exception e) {
               System.err.println("Got an exception! ");
               System.err.println(e.getMessage());
           }
       } else {
           try {
               Connection conn = DatabaseConnecter.connectToDatabase();
               String query = "delete from Motor_Bike where plateNumber = ?";
               PreparedStatement preparedStmt = conn.prepareStatement(query);
               preparedStmt.setString(1, number);
               // execute the preparedstatement
               preparedStmt.execute();

               //To check available slots
               String query2 = "select (select COUNT(*) from car) AS count1, ( select COUNT(*) from motor_bike) AS count2 FROM dual";
               Statement stmt = conn.createStatement();
               ResultSet rs = stmt.executeQuery(query2);
               rs.next();
               int numOfCars = rs.getInt(1);
               int numOfMB = rs.getInt(2);
               System.out.println("Available Parking Lots for Cars "+(50-numOfCars-numOfMB));
               System.out.println(numOfCars);
               System.out.println(numOfMB);



               conn.close();
               System.out.println("Motor Bike "+number+" deleted");
           } catch (Exception e) {
               System.err.println("Got an exception! ");
               System.err.println(e.getMessage());
           }
       }
   }



    @Override
    public void editVehicle(int vNum2,Vehicle vehicle) {
          if (vNum2==1) {

              String sqlCode = "update car set make=?,rate=?, model=?,fuelType=?,numberOfAirbags=?,carColour=? where plateNumber=?";
              try {
                  Connection conn = DatabaseConnecter.connectToDatabase();
                  PreparedStatement stm = conn.prepareStatement(sqlCode, Statement.RETURN_GENERATED_KEYS);
                  stm.setString(1,vehicle.getMake());
                  stm.setBigDecimal(2, vehicle.getRate());
                  stm.setString(3, vehicle.getModel());
                  stm.setString(4,  vehicle.getFuelType());
                  stm.setInt(5,((Car)vehicle).getNumberOfAirbags());
                  stm.setString(6,((Car)vehicle).getCarColour());

                  stm.setString(7,vehicle.getPlateNumber());
                  stm.executeUpdate();

              } catch (SQLException e) {
                  System.err.println(e);
              }
          }
        if (vNum2==2) {
            String sqlCode = "update motor_bike set make=?,rate=?, model=?,fuelType=?,bikeType=?,startType=? where plateNumber=?";
            try {
                Connection conn = DatabaseConnecter.connectToDatabase();
                PreparedStatement stm = conn.prepareStatement(sqlCode, Statement.RETURN_GENERATED_KEYS);
                stm.setString(1,vehicle.getMake());
                stm.setBigDecimal(2, vehicle.getRate());
                stm.setString(3, vehicle.getModel());
                stm.setString(4,  vehicle.getFuelType());
                stm.setString(5,((MotorBike)vehicle).getBikeType());
                stm.setString(6,((MotorBike)vehicle).getStartType());

                stm.setString(7,vehicle.getPlateNumber());
                stm.executeUpdate();

            } catch (SQLException e) {
                System.err.println(e);
            }

        }
    }




    @Override
    public void save() throws IOException {

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

        }


        File file = new File("./VehicleList.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file,true);
        BufferedWriter bw = new BufferedWriter(fw);
        fw.write("*******************************************************************************\n");
        bw.newLine();
        for (int i = 0; i < cars.size(); i++) {
            bw.write(cars.get(i).toString());
            bw.newLine();

        }

        bw.flush();
        bw.close();


        List<MotorBike> bikes = new ArrayList<MotorBike>();
        sqlCode = "select  * from  motor_bike";
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

    }
         file = new File("./VehicleList.txt");
        if (!file.exists()) {
            file.createNewFile();
        }
         fw = new FileWriter(file,true);
         bw = new BufferedWriter(fw);
        bw.newLine();
        for (int i = 0; i < bikes.size(); i++) {
            bw.write(bikes.get(i).toString());
            bw.newLine();

        }

        bw.flush();
        bw.close();


    }


    @Override
    public void printList() {

        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;


        System.out.println("Car List\n");
            try{
                Connection conn = DatabaseConnecter.connectToDatabase();
                            ps=conn.prepareStatement("select * from car order by make");

            rs=ps.executeQuery();
                System.out.println("Plate Number     Make      Rate     Model   FuelType   NumberOfAirBags  Car Colour" );
            while(rs.next())
            {
                System.out.print(rs.getString(1)); //here you can get data, the '1' indicates column number based on your query
                System.out.printf("%14s",rs.getString(2));
                System.out.printf("%11s",rs.getString(3));
                System.out.printf("%10s",rs.getString(4));
                System.out.printf("%10s",rs.getString(5));
                System.out.printf("%10s",rs.getString(6));
                System.out.printf("%17s",rs.getString(7));
                System.out.println();

            }

        }
      catch(Exception e)
        {
            System.out.println("Error in getData"+e);
        }
        System.out.println();
        System.out.println("MotorBike List\n");
        try{
            Connection conn = DatabaseConnecter.connectToDatabase();
            ps=conn.prepareStatement("select * from motor_bike order by make");

            rs=ps.executeQuery();
            System.out.println("Plate Number     Make      Rate     Model   FuelType   BikeType      StartType" );
            while(rs.next())
            {
                System.out.print(rs.getString(1));
                System.out.printf("%14s",rs.getString(2));
                System.out.printf("%11s",rs.getString(3));
                System.out.printf("%10s",rs.getString(4));
                System.out.printf("%10s",rs.getString(5));
                System.out.printf("%10s",rs.getString(6));
                System.out.printf("%17s",rs.getString(7));
                System.out.println();
            }

        }
        catch(Exception e)
        {
            System.out.println("Error in getData"+e);
        }
        }


    public boolean runMenu() throws IOException, SQLException {

        boolean exit = false;
        Connection conn = DatabaseConnecter.connectToDatabase();
        String query2 = "select (select COUNT(*) from car) AS count1, ( select COUNT(*) from motor_bike) AS count2 FROM dual";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(query2);
        rs.next();
        int numOfCars = rs.getInt(1);
        int numOfMB = rs.getInt(2);
        int NumberOfSlots = numOfCars + numOfMB;
        System.out.println("*****************************************");
        if (NumberOfSlots < 50) {
            System.out.println("To Add a new Vehicle press 1");
            System.out.println("To print the list of the Vehicles press 2");
            System.out.println("To exit press 3");
            System.out.println("To delete vehicle press 4");
            System.out.println("To edit vehicle press 5");
        }else {
            System.out.println("To print the list of the Vehicles press 2");
            System.out.println("To exit press 3");
            System.out.println("To delete vehicle press 4");
            System.out.println("To edit vehicle press 5");
        }
        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Press 1 if you want to add a Car");
                System.out.println("Press 2 if you want to add a Bike");
                System.out.println("Press 3 if you want to add a Bus");

                int choice2 = s.nextInt();
                s.nextLine();

                System.out.println("Enter the Plate Number");
                String plateNumber = s.nextLine();

                System.out.println("Enter the Make");
                String make = s.next();

                System.out.println("Enter the Rate");
                BigDecimal rate = s.nextBigDecimal();

                System.out.println("Enter the Model");
                String model = s.next();

                System.out.println("Enter the Fuel Type");
                String fuelType = s.next();


                switch (choice2) {
                    case 1:
                        // it is a car
                        System.out.println("Insert the NumOfAirBags");
                        int numOfAirBags = s.nextInt();
                        System.out.println("Insert the Car Colour");
                        String carColour = s.next();
                        Car c = new Car(numOfAirBags, carColour, plateNumber, make, rate, model, fuelType);
                        addVehicle(c);
                        break;

                    case 2:
                        // it is a Bike
                        System.out.println("Insert the Bike Type");
                        String bikeType = s.next();

                        System.out.println("Insert the StartType");
                        String startType = s.next();

                        MotorBike m = new MotorBike(bikeType, startType, plateNumber, make, rate, model, fuelType);
                        addVehicle(m);
                        break;

                    case 3:
                        // it is a Bus
                        System.out.println("Insert the Bus Type");
                        String busType = s.nextLine();

                        System.out.println("Insert the Door Type");
                        String doorType = s.next();
                        Bus b = new Bus(busType, doorType, plateNumber, make, model);
                        //b.addVehicle();
                        break;

                }
                save();
                break;

            case 2:
                printList();
                break;

            case 3:
                exit = true;
                break;

            case 4:
                System.out.println("Car(1) or Motor Bike(2)");
                int vNum=s.nextInt();
                System.out.println("Give Plate Number");
                String number = s.next();
                Car cd =new Car();
                deleteVehicle(vNum,number);
                save();
                break;

            case 5:
                System.out.println("Car(1) or Motor Bike(2)");
                int vNum2=s.nextInt();

                System.out.println("Enter the Plate Number");
                 plateNumber = s.next();

                System.out.println("Enter the Make");
                 make = s.next();

                System.out.println("Enter the Rate");
                 rate = s.nextBigDecimal();

                System.out.println("Enter the Model");
                 model = s.next();

                System.out.println("Enter the Fuel Type");
                 fuelType = s.next();


                if (vNum2==1) {
                    System.out.println("Insert the NumOfAirBags");
                    int numOfAirBags = s.nextInt();
                    System.out.println("Insert the Car Colour");
                    String carColour = s.next();

                    Car c = new Car(numOfAirBags, carColour, plateNumber, make, rate, model, fuelType);
                    editVehicle(vNum2, c);
                }
                else {
                    System.out.println("Insert the Bike Type");
                    String bikeType = s.next();

                    System.out.println("Insert the StartType");
                    String startType = s.next();

                    MotorBike m = new MotorBike(bikeType, startType, plateNumber, make, rate, model, fuelType);
                    editVehicle(vNum2,m);
                }
                save();
                break;
        }

        return exit;
    }


    public static void main(String[] args) throws IOException, SQLException {

       RentalVehicleManager rvm =new WestminsterRentalVehicleManager();
        boolean exit = false;
        while (!exit){
            exit = rvm.runMenu();
        }

    }

        }