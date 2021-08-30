package vehicle.service;

import exceptions.BookingException;
import exceptions.VehicleException;
import vehicle.model.Vehicle;

public interface I_VehicleService {
    Boolean registerVehicle(Vehicle vehicle) throws VehicleException;
    Boolean updateLocation(Vehicle vehicle) throws VehicleException;
    Vehicle find(Long cityId) throws VehicleException;
}
