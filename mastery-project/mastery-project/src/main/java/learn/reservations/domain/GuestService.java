package learn.reservations.domain;

import learn.reservations.data.DataException;
import learn.reservations.data.GuestRepository;
import learn.reservations.models.Guest;

import java.util.List;
import java.util.stream.Collectors;


public class GuestService {
    private final GuestRepository guestRepository;
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> findByGuestEmail(String guestEmail) throws DataException {
        return guestRepository.findAll().stream()
                .filter(g -> g.getEmail().equalsIgnoreCase(guestEmail))
                .collect(Collectors.toList());
    }
}
