package learn.reservations.data;

import learn.reservations.models.Guest;
import learn.reservations.models.Host;
import learn.reservations.models.Reservation;

import java.io.*;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReservationFileRepository implements ReservationRepository {
    private static final String HEADER = "id,start_date,end_date,guest_id,total";
    private final String directory;

    public ReservationFileRepository(String directory) {
        this.directory = directory;
    }

    @Override
    public List<Reservation> findByHost(Host host) {
        ArrayList<Reservation> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(getFilePath(host)))) {
            reader.readLine(); // read header
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] fields = line.split(",", -1);
                if (fields.length == 5) {
                    result.add(deserialize(fields));
                }
            }
        } catch (IOException ex) {

        }
        return result;
    }

    @Override
    public Reservation add(Reservation reservation) throws DataException { //HostId?
        List<Reservation> all = findByHost(reservation.getHost());
        reservation.setHostId(java.util.UUID.randomUUID().toString());
        all.add(reservation);
        writeAll(all, reservation.getHost());
        return reservation;
    }


//    @Override
//    public boolean update(Reservation reservation) throws DataException {
//        List<Reservation> all = findByHost(host);
//        for (int i = 0; i < all.size(); i++) {
//            if (all.get(i).getId() == reservation.getId()) {
//                all.set(i, reservation);
//                writeAll(all, reservation.getHostId());
//                return true;
//            }
//        }
//        return false;
//    }

//    @Override
//    public boolean deleteById(int reservationId) throws DataException { //???
//        List<Reservation> all = findByHost(String.valueOf(reservationId));
//        for (int i = 0; i < all.size(); i++) {
//            if (all.get(i).getId() == reservationId) {
//                all.remove(i);
//                writeAll(all, String.valueOf(reservationId));
//                return true;
//            }
//        }
//        return false;
//    }

    private String getFilePath(Host host) {
        return Paths.get(directory, host.getHostId() + ".csv").toString();
    }

    private void writeAll(List<Reservation> reservations, Host host) throws DataException {
        try (PrintWriter writer = new PrintWriter(getFilePath(host))) {

            writer.println("id,start_date,end_date,guest_id,total");

            for (Reservation r : reservations) {
                writer.println(serialize(r));
            }
        } catch (FileNotFoundException ex) {
            throw new DataException(ex);
        }
    }

    private String serialize(Reservation reservation) {
        return String.format("%s,%s,%s,%s,%s",
                reservation.getId(),
                reservation.getStartDate(),
                reservation.getEndDate(),
                reservation.getGuest().getGuestId(),
                reservation.getTotal());
    }

    //how to set a host??
    private Reservation deserialize(String[] fields) {
        Reservation result = new Reservation();
        result.setId(Integer.parseInt(fields[0]));
        result.setStartDate(LocalDate.parse(fields[1]));
        result.setEndDate(LocalDate.parse(fields[2]));

        Guest guest = new Guest();
        result.setGuestId(Integer.parseInt(fields[3]));
        result.setGuest(guest);

        result.setTotal(BigDecimal.valueOf(Long.parseLong(fields[4])));
        return result;
    }
}


