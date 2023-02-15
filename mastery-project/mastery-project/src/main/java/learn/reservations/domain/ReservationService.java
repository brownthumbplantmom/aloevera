package learn.reservations.domain;

import learn.reservations.data.DataException;
import learn.reservations.data.GuestRepository;
import learn.reservations.data.HostRepository;
import learn.reservations.data.ReservationRepository;
import learn.reservations.models.Guest;
import learn.reservations.models.Host;
import learn.reservations.models.Reservation;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final HostRepository hostRepository;
    private final GuestRepository guestRepository;

    public ReservationService(ReservationRepository reservationRepository, HostRepository hostRepository, GuestRepository guestRepository) {
        this.reservationRepository = reservationRepository;
        this.hostRepository = hostRepository;
        this.guestRepository = guestRepository;
    }

    public List<Reservation> findByHost(Host host) throws DataException {
        Map<Integer, Guest> guestMap = guestRepository.findAll().stream()
                .collect(Collectors.toMap(g -> g.getGuestId(), //tells toMap method to retrieve key
                        g -> g)); //take the guest object and make that the value
        List<Reservation> reservations = reservationRepository.findByHost(host);

        for (Reservation reservation : reservations) {
//            int guestId = reservation.getGuestId();
//            Guest guest = guestMap.get(guestId);
//            reservation.setGuest(guest);
            //equivalent to nested line below
            reservation.setGuest(guestMap.get(reservation.getGuestId())); //this did not work
        }
        return reservations;
    }

    private Result<Reservation> validateHost(Reservation reservation) {
        Result<Reservation> result = new Result();

        if (reservation == null) {
            result.addErrorMessage("No host found.");
            return result;
        } else {
            List<Reservation> reservations = reservationRepository.findByHost(reservation.getHost());
            if (reservations == null || reservations.isEmpty()) {
                result.addMessage("Host has no reservations.");
            }
        }
        return result;
    }

    public Result<Reservation> add(Reservation reservation) throws DataException {
        Result<Reservation> result = validate(reservation);
        if (!result.isSuccess()) {
            return result;
        }
        result.setPayload(reservationRepository.add(reservation));
        return result;
    }

    private Result<Reservation> validate(Reservation reservation) throws DataException {
        Result<Reservation> result = new Result<>();
        //validate start date
        LocalDate currentDate = LocalDate.now();
        if (reservation.getStartDate().isBefore(currentDate)) {
            result.addErrorMessage("Start date cannot be before current date.");
        }
        //validate end date
        if (reservation.getEndDate().isBefore(reservation.getStartDate())) {
            result.addErrorMessage("End date cannot be before start date.");
        }
        //exercise checking for overlap
//        public boolean add(MicroLease lease) {
//            if (lease == null || lease.getStart() == null || lease.getEnd() == null || lease.getStart().isAfter(lease.getEnd())) {
//                return false;
//            }
//            //if start of new lease is before the end of a current lease && end of the new lease is after the start of the current lease
//            for (MicroLease ml : leases) {
//                if (lease.getStart().isBefore(ml.getEnd()) && lease.getEnd().isAfter(ml.getStart())) {
//                    return false;
//                }
//            }

//        if (!result.isSuccess()) {
//            return result.getMessages();
        return result;
    }



//    public Result deleteById(int id) throws DataException {
//        Result result = new Result();
//
//        if(!reservationRepository.deleteById(id)) {
//            String message = String.format("Reservation id %s was not found", id);
//            result.addErrorMessage(message);
//        }
//        return result;
//    }

    public BigDecimal calculateRate(Host host, LocalDate startDate, LocalDate endDate) {
        BigDecimal totalRate = BigDecimal.ZERO;

        LocalDate currentDate = startDate;
        while (!currentDate.isAfter(endDate)) {
            if (currentDate.getDayOfWeek() == DayOfWeek.FRIDAY || currentDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                totalRate = totalRate.add(host.getWeekendRate());
            } else {
                totalRate = totalRate.add(host.getStandardRate());
            }
            currentDate = currentDate.plusDays(1);
        }

        return totalRate;
    }
}



