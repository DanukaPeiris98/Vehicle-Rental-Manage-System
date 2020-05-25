import java.io.IOException;
import java.sql.SQLException;

public interface RentalVehicleManager {

    public void addVehicle(Vehicle vehicle) throws SQLException;
    public void editVehicle(int vNum2,Vehicle vehicle);
    public void deleteVehicle(int vNum,String number);
    public void save() throws IOException;
    public void printList();
    public  boolean runMenu() throws IOException, SQLException;
}
