package vehicle.service;

import exceptions.BookingException;
import exceptions.VehicleException;
import storage.I_StorageService;
import storage.StorageServiceImpl;
import vehicle.model.Vehicle;

public class VehicleServiceImpl implements I_VehicleService{
    I_StorageService storageService;
    public VehicleServiceImpl(I_StorageService storageService){
        this.storageService = storageService;
    }
    @Override
    public Boolean registerVehicle(Vehicle vehicle) throws VehicleException {
        return this.storageService.saveVehicle(vehicle);
    }

    @Override
    public Boolean updateLocation(Vehicle vehicle) throws VehicleException {
        return this.storageService.updateLocation(vehicle);
    }

    @Override
    public Vehicle find(Long cityId) throws VehicleException {
        Vehicle vehicle = this.storageService.find(cityId);
        if(vehicle == null){
            throw new VehicleException("Vehicle not available");
        }
        return vehicle;
    }
}
