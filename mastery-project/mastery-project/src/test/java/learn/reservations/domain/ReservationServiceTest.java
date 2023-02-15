/*package learn.reservations.domain;

import learn.reservations.data.ReservationFileRepository;
import learn.reservations.models.Host;
import learn.reservations.models.Reservation;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ReservationServiceTest {
    private ReservationFileRepository reservationFileRepository = new ReservationFileRepository("./data/reservation");
    private ReservationService service = new ReservationService(reservationFileRepository);

    @Test
    void shouldFindByHost() {
        Host testHost = new Host();
        testHost.setHostId("2e72f86c-b8fe-4265-b4f1-304dea8762db");
        List<Reservation> reservations = service.findByHost(testHost);
    }

}

 */
