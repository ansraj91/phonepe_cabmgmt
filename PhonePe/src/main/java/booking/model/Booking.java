package booking.model;

import booking.status.BOOKING_STATUS;

public class Booking {
    private String bookingId;
    private Long riderUserId;
    private String carNumber;
    private long startTime;
    private long endTime;
    private BOOKING_STATUS status;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Long getRiderUserId() {
        return riderUserId;
    }

    public void setRiderUserId(Long riderUserId) {
        this.riderUserId = riderUserId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public BOOKING_STATUS getStatus() {
        return status;
    }

    public void setStatus(BOOKING_STATUS status) {
        this.status = status;
    }

    public Booking() {

    }


}
