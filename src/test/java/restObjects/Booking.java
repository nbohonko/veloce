package restObjects;

import lombok.Data;

@Data
public class Booking {
    public String firstname;
    public String lastname;
    public int totalprice;
    public boolean depositpaid;
    public Bookingdates bookingdates;
    public String additionalneeds;

    @Data
    public static class Bookingdates {
        public String checkin;
        public String checkout;
    }
}
