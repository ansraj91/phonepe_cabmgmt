import booking.service.BookingServiceImpl;
import booking.service.I_BookingService;
import exceptions.RiderException;
import exceptions.VehicleException;
import rider.models.Rider;
import rider.service.I_RiderService;
import rider.service.RiderServiceImpl;
import storage.I_StorageService;
import storage.StorageServiceImpl;
import vehicle.model.Vehicle;
import vehicle.service.I_VehicleService;
import vehicle.service.VehicleServiceImpl;
import vehicle.type.VehicleType;

import java.util.UUID;

public class App {
    private static I_StorageService storageService = new StorageServiceImpl();
    private static I_RiderService riderService = new RiderServiceImpl(storageService);
    // private static I_DriverService driverService = new DriverServiceImpl(storageService);
    private static I_VehicleService vehicleService = new VehicleServiceImpl(storageService);
    private static I_BookingService bookingService = new BookingServiceImpl(vehicleService, storageService);
    public static void main(String[] args) throws RiderException, VehicleException {

        Rider rider = new Rider();
        rider.setId(1L);
        rider.setName("harsh");
        rider.setContactNumber("+91910");
        riderService.register(rider);



        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleNumber("KA01HK");
        vehicle.setCityId(101L);
        vehicle.setType(VehicleType.CAR);
        vehicleService.registerVehicle(vehicle);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setVehicleNumber("KA01HK23");
        vehicle1.setCityId(101L);
        vehicle1.setType(VehicleType.CAR);
        vehicleService.registerVehicle(vehicle1);


        bookingService.book(1L, 101L);
        bookingService.book(1L, 101L);
        bookingService.book(1L, 101L);

    }
}
