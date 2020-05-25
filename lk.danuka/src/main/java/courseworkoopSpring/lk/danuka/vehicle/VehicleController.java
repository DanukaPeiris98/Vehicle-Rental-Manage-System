package courseworkoopSpring.lk.danuka.vehicle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@RestController
public class VehicleController {
    @Autowired
    private VehicleService vehicleService;

//Get All Cars from database
    @RequestMapping(method = RequestMethod.GET, value = "/cars")
    public List<Car> getAllCars() {
        return vehicleService.getCarsDB();
    }
//Get All Bikes from database
    @RequestMapping(method = RequestMethod.GET, value = "/motorBikes")
    public List<MotorBike> getAllMotorBikes() {
        return vehicleService.getMotorBikeDB();
    }
//Filtering Cars using make element
    @RequestMapping(method = RequestMethod.GET, value = "/filteringCars")
    public List<Car> getFilCars(@RequestParam String make) {
        return vehicleService.getCarsMakeDB(make);
    }
//Filtering Bike using make element
    @RequestMapping(method = RequestMethod.GET, value = "/filteringBikes")
    public List<MotorBike> getFilMotorBikes(@RequestParam String make) {
        return vehicleService.getBikesMakeDB(make);
    }
//Give Plate Number and Date for orders
    @RequestMapping(method = RequestMethod.GET, value = "/filteringDateMake")
    public Map<String, String> getFilMotorBikes(@RequestParam String plateNumber, Date pickup, Date drop) {
        return vehicleService.getScheduleDB(plateNumber, pickup, drop );
    }

    @RequestMapping(method = RequestMethod.POST, value = "/insertOrder")
    public Map<String, String> setOrder(@RequestBody Map<String, String> request) {

        return vehicleService.setScheduleDB(request.get("plateNumber"),Date.valueOf(request.get("pickup")),Date.valueOf(request.get("drop")));
    }
//Get Car Make elements
    @RequestMapping(method = RequestMethod.GET, value = "/carMake")
    public List<String> getCarMakes() {
        return vehicleService.getCarMakeDB();
    }
    //Get Bike Make elements
        @RequestMapping(method = RequestMethod.GET, value = "/bikeMake")
        public List<String> getBikeMakes() {
            return vehicleService.getBikeMakeDB();
        }

}
