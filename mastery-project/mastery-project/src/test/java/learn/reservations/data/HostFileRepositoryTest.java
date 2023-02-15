package learn.reservations.data;

import learn.reservations.models.Guest;
import learn.reservations.models.Host;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HostFileRepositoryTest {
    static final String SEED_FILE_PATH = "./data/hosts-seed.csv";
    static final String TEST_FILE_PATH = "./data/hosts-test.csv";

    HostFileRepository repository = new HostFileRepository(TEST_FILE_PATH);

//    @Test
//    void shouldFindAll() throws DataException {
//        HostFileRepository repo = new HostFileRepository("./data/hosts.csv");
//        List<Host> all = repo.findAll();
//        assertEquals(1000, all.size());
//    }

}
