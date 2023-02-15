package learn.reservations.data;

import learn.reservations.models.Guest;

import java.util.List;

public class GuestRepositoryDouble implements GuestRepository {
//    guest_id,first_name,last_name,email,phone,state
//    private final static Guest guest = new Guest(1, "Mo", "A", "moa@yahoo.com", "2625422551", "MO");
    @Override
    public List<Guest> findAll() throws DataException {
        return null;
    }
}
