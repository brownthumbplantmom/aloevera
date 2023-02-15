package learn.reservations.data;

import learn.reservations.models.Guest;
import learn.reservations.models.Reservation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GuestFileRepositoryTest {
    static final String SEED_PATH = "./data/guest-seed.txt";
    static final String TEST_PATH = "./data/guest-test.txt";

    static final int NEXT_ID = 1001;

    GuestFileRepository repository = new GuestFileRepository(TEST_PATH);

    @BeforeEach
    void setup() throws IOException {
        Path seedPath = Paths.get(SEED_PATH);
        Path testPath = Paths.get(TEST_PATH);
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void shouldFindAll() throws DataException {
        assertTrue(repository.findAll().size() == NEXT_ID -1);
    }
//    @Test
//    void shouldFindEmail() throws DataException {
//        List<Guest> guest = repository.findByGuestEmail("kcurson5@youku.com");
//    }
//    @Test
//    void shouldNotFindEmail() {
//
//
//    }


}
