package learn.reservations.data;

import learn.reservations.models.Guest;

import java.util.List;

public interface GuestRepository {
    List<Guest> findAll() throws DataException;
}
