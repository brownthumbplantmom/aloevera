package learn.reservations.data;

import learn.reservations.models.Host;
import learn.reservations.models.Reservation;

import java.util.List;

public interface ReservationRepository {

    List<Reservation> findByHost(Host host);

    Reservation add(Reservation reservation) throws DataException;

//    boolean update(Reservation reservation, Host host) throws DataException;
}
