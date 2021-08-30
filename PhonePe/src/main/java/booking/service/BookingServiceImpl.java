package booking.service;

import booking.model.Booking;
import booking.status.BOOKING_STATUS;
import exceptions.BookingException;
import exceptions.RiderException;
import exceptions.VehicleException;
import storage.I_StorageService;
import vehicle.model.Vehicle;
import vehicle.service.I_VehicleService;

import java.util.List;
import java.util.UUID;

public class BookingServiceImpl implements I_BookingService{
    private I_VehicleService vehicleService;
    private I_StorageService storageService;

    public BookingServiceImpl(I_VehicleService vehicleService,I_StorageService storageService){
        this.storageService = storageService;
        this.vehicleService = vehicleService;
    }

    @Override
    public Booking book(Long riderUserId,Long cityId) throws VehicleException {
        Vehicle vehicle = vehicleService.find(cityId);
        //TODO lock the cab
        Booking booking = new Booking();
        booking.setBookingId(UUID.randomUUID().toString());
        booking.setCarNumber(vehicle.getVehicleNumber());
        booking.setRiderUserId(riderUserId);
        booking.setStatus(BOOKING_STATUS.BOOKED);
        storageService.book(booking);
        return booking;
    }

    @Override
    public List<Booking> history(Long riderUserId) throws RiderException {
        List<Booking> bookingHistory = storageService.rideHistory(riderUserId);
        return bookingHistory;
    }

    @Override
    public Boolean endTrip(Long timeStamp, String bookingId) throws BookingException {
        storageService.endTrip(timeStamp, bookingId);
        return true;
    }


}
