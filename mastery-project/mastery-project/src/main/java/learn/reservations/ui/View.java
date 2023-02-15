package learn.reservations.ui;

import learn.reservations.domain.Result;
import learn.reservations.models.Guest;
import learn.reservations.models.Host;
import learn.reservations.models.Reservation;

import java.time.LocalDate;
import java.util.List;

public class View {
    private final ConsoleIO io;

    public View(ConsoleIO io) {
        this.io = io;
    }

    public MainMenuOption selectMainMenuOption() { //GOOD
        displayHeader("Main Menu");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (MainMenuOption option : MainMenuOption.values()) {
            if (!option.isHidden()) {
                io.printf("%s. %s%n", option.getValue(), option.getMessage());
            }
            min = Math.min(min, option.getValue());
            max = Math.max(max, option.getValue());
        }
        String message = String.format("Select [%s-%s]: ", min, max);
        return MainMenuOption.fromValue(io.readInt(message, min, max));
    }

    public void enterToContinue() { //GOOD
        io.readString("Press [Enter] to continue.");
    }

    public void displayHeader(String message) { //GOOD
        io.println("");
        io.println(message);
        io.println("=".repeat(message.length()));
    }

    public void displayException(Exception ex) { //GOOD
        displayHeader("A critical error occurred:");
        io.println(ex.getMessage());
    }
    public void displayStatus(boolean success, String message) {
        displayStatus(success, List.of(message));
    }

    public void displayStatus(boolean success, List<String> messages) {
        displayHeader(success ? "Success" : "Error");
        for (String message : messages) {
            io.println(message);
        }
    }

    public void displayMessage(String s) {
        System.out.println(s);
    }

    public void displayHostEmail(String hostEmail) {
        printHeader("View Reservations for Host");
        printHeader("Host Email: " + hostEmail);
    }

    public void displayReservations(List<Reservation> reservations) {
        for (Reservation reservation : reservations) { //needs to be in service
            if (reservations == null || reservations.isEmpty()) {
                io.println("No reservations found.");
            }
            io.printf("ID: %d, %s - %s, Guest: %s, %s, Email: %s%n",
                    reservation.getId(),
                    reservation.getStartDate(),
                    reservation.getEndDate(),
                    reservation.getGuest().getLastName(),
                    reservation.getGuest().getFirstName(),
                    reservation.getGuest().getEmail()
            );
        }
    }

    public Reservation makeReservation(Host host, Guest guest) {
        Reservation reservation = new Reservation();
        host.setEmail(io.readRequiredString("Host email: "));
        guest.setEmail(io.readRequiredString("Guest email: "));


        reservation.setStartDate(io.readLocalDate("Start date [MM/dd/YYYY]: "));
        reservation.setEndDate(io.readLocalDate("End date [MM/dd/YYYY]: "));

        return reservation;
    }



    public String getHostEmail() {
        return io.readRequiredString("Host email: ");
    }
    public String getGuestEmail() {
        return io.readRequiredString("Guest email: ");
    }

    //public LocalDate getForageDate() {
    //        displayHeader(MainMenuOption.VIEW_FORAGES_BY_DATE.getMessage());
    //        return io.readLocalDate("Select a date [MM/dd/yyyy]: ");
    //    }

//
//    public void printResult(Result result) {
//        if (result.isSuccess()) {
//            printHeader("[Success]");
//        } else {
//            printHeader("Err: ");
//            for (String err : result.getErrorMessages()) {
//                System.out.println(err);
//            }
//        }
//    }

    public void printHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

//    public boolean confirmReservation(String s) {
//        return io.readBoolean("Confirm [Y/N]: ");
//    }

}
