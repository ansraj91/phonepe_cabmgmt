package booking.service;

import booking.model.Booking;
import exceptions.BookingException;
import exceptions.RiderException;
import exceptions.VehicleException;

import java.util.List;

public interface I_BookingService {
    Booking book(Long riderUserId, Long cityId) throws VehicleException;
    List<Booking> history(Long riderUserId) throws RiderException;
    Boolean endTrip(Long timeStamp, String bookingId) throws BookingException;
}
