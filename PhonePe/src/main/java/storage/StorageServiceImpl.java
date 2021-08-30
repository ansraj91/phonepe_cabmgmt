package storage;

import booking.model.Booking;
import exceptions.BookingException;
import exceptions.RiderException;
import exceptions.VehicleException;
import rider.models.Rider;
import vehicle.model.Vehicle;
import vehicle.state.VehicleStateIdle;

import javax.print.attribute.standard.MediaSize;
import java.util.*;

import static booking.status.BOOKING_STATUS.COMPLETED;

public class StorageServiceImpl implements I_StorageService{
    private Map<Long, Rider> riderStorage;
  //  private Map<String, Driver> driverStorage;
    private Map<String, Vehicle> vehicleStorage;
    private Map<String, Booking> bookingStorage;
    private Map<Long, List<String>> cityVehicleMap;

    public StorageServiceImpl() {
        this.riderStorage = new HashMap<>();
       // this.driverStorage = new HashMap<>();
        this.vehicleStorage = new HashMap<>();
        this.bookingStorage = new HashMap<>();
        this.cityVehicleMap = new HashMap<>();
    }

    @Override
    public Boolean saveRider(Rider rider) throws RiderException {
        StringBuffer sb = new StringBuffer();
        if(!riderStorage.containsKey(rider.getId())){
           riderStorage.put(rider.getId(), rider);
        }else{
            throw new RiderException("Rider Details already Exists");
        }
        return true;
    }

    @Override
    public Boolean saveVehicle(Vehicle vehicle) throws VehicleException {
        if(this.vehicleStorage.get(vehicle.getVehicleNumber()) != null){
            throw new VehicleException("Vehicle already exist in the system");
        }
        this.vehicleStorage.put(vehicle.getVehicleNumber(), vehicle);
        if(!cityVehicleMap.containsKey(vehicle.getCityId())){
            List<String> list = new ArrayList<>();
            list.add(vehicle.getVehicleNumber());
            cityVehicleMap.put(vehicle.getCityId(),list);
         }else{
            cityVehicleMap.get(vehicle.getCityId()).add(vehicle.getVehicleNumber());
        }

        return true;
    }

    @Override
    public Boolean updateLocation(Vehicle vehicle) throws VehicleException {
        if(this.vehicleStorage.get(vehicle.getVehicleNumber()) == null){
            throw new VehicleException("Vehicle does not exist in the system");
        }
        Vehicle vehicleInDb = this.vehicleStorage.get(vehicle.getVehicleNumber());
        vehicleInDb.setCityId(vehicle.getCityId());
        this.vehicleStorage.put(vehicle.getVehicleNumber(), vehicleInDb);
        return true;
    }

    @Override
    public Boolean book(Booking booking) {
        this.bookingStorage.put(booking.getBookingId(), booking);
        Rider rider = this.riderStorage.get(booking.getRiderUserId());
        List<String> bookingHistory = rider.getBookingHistory();
        if(bookingHistory == null){
            bookingHistory = new ArrayList<>();
        }
        bookingHistory.add(booking.getBookingId());
        rider.setBookingHistory(bookingHistory);
        this.riderStorage.put(booking.getRiderUserId(), rider);
        vehicleStorage.get(booking.getCarNumber()).update();
        return true;
    }

    @Override
    public Vehicle find(Long cityId) throws VehicleException {
        if(!cityVehicleMap.containsKey(cityId)){
            throw new VehicleException("Vehicle not available in the city");
        }
      List<String> vehicleList = cityVehicleMap.get(cityId);
      for(String vehicleNo : vehicleList){
          Vehicle vehicle = vehicleStorage.get(vehicleNo);
          if(vehicle.getCurrentState().equals(VehicleStateIdle.instance())){
              return vehicle;
          }
      }
        return null;
    }

    @Override
    public List<Booking> rideHistory(Long riderUserID) throws RiderException {
        Rider rider = this.riderStorage.get(riderUserID);
        if(rider==null){
            throw new RiderException("Rider UID not available");
        }
        List<String> riderBookingIdsHistory = rider.getBookingHistory();
        List<Booking> bookingHistory = new ArrayList<>();
        for(String bookingId : riderBookingIdsHistory){
            Booking booking = this.bookingStorage.get(bookingId);
            bookingHistory.add(booking);
        }
        return bookingHistory;
    }

    @Override
    public Boolean endTrip(Long timeStamp, String bookingId) throws BookingException {
        Booking booking = this.bookingStorage.get(bookingId);
        if(booking == null){
            throw new BookingException("No trip by this Id");
        }
        if(booking.getStatus() != null){
            throw new BookingException("Booking already ended");
        }
        booking.setEndTime(timeStamp);
        booking.setStatus(COMPLETED);
        vehicleStorage.get(booking.getCarNumber()).update();
        return true;
    }
}
