package storage;

import booking.model.Booking;
import exceptions.BookingException;
import exceptions.RiderException;
import exceptions.VehicleException;
import rider.models.Rider;
import vehicle.model.Vehicle;

import java.util.List;

public interface I_StorageService {
    Boolean saveRider(Rider rider) throws RiderException;
    Boolean saveVehicle(Vehicle vehicle) throws VehicleException;
    Boolean updateLocation(Vehicle vehicle) throws VehicleException;
    Boolean book(Booking book);
    Vehicle find(Long cityid) throws VehicleException;
    List<Booking> rideHistory(Long riderUserID) throws RiderException;
    Boolean endTrip(Long timeStamp, String bookingId) throws BookingException;
}
