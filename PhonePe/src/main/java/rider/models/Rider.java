package rider.models;

import java.util.List;

public class Rider extends I_PersonalInformation{
    private List<String> bookingHistory;

    public Rider() {
        super();
    }

    public List<String> getBookingHistory() {
        return bookingHistory;
    }

    public void setBookingHistory(List<String> bookingHistory) {
        this.bookingHistory = bookingHistory;
    }

    public Rider(Long id, String name, String contactNumber) {
        super(id, name, contactNumber);
    }
}
