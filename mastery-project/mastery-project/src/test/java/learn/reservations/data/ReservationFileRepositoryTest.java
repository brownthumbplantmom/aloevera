package learn.reservations.data;

import learn.reservations.models.Host;
import learn.reservations.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

class ReservationFileRepositoryTest {
    static final String SEED_FILE_PATH = "./data/reservation-seed-2e72f86c-b8fe-4265-b4f1-304dea8762db.csv";
    static final String TEST_FILE_PATH = "./data/reservation-data-test/2e72f86c-b8fe-4265-b4f1-304dea8762db.csv";
    static final String TEST_DIR_PATH = "./data/reservation-data-test";
    static final int RESERVATION_COUNT = 12;

//    hostId = 2e72f86c-b8fe-4265-b4f1-304dea8762db

    ReservationFileRepository repository = new ReservationFileRepository(TEST_DIR_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_FILE_PATH);
        Path testPath = Paths.get(TEST_FILE_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

//    @Test
//    void shouldFindHost() {
//
//    }

}
