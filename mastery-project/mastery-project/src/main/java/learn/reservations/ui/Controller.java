package learn.reservations.ui;

import learn.reservations.data.DataException;
import learn.reservations.domain.GuestService;
import learn.reservations.domain.HostService;
import learn.reservations.domain.ReservationService;
import learn.reservations.domain.Result;
import learn.reservations.models.Guest;
import learn.reservations.models.Host;
import learn.reservations.models.Reservation;

import java.util.List;

public class Controller {
    private final View view;
    private final ReservationService reservationService;
    private final HostService hostService;
    private final GuestService guestService;

    public Controller(View view, ReservationService reservationService, HostService hostService, GuestService guestService) {
        this.view = view;
        this.reservationService = reservationService;
        this.hostService = hostService;
        this.guestService = guestService;
    }

    public void run() { //GOOD
        view.displayHeader("Don't Wreck My House");
        try {
            runAppLoop();
        } catch (DataException ex) {
            view.displayException(ex);
        }
        view.displayHeader("Goodbye.");
    }

    private void runAppLoop() throws DataException { //GOOD
        MainMenuOption option;
        do {
            option = view.selectMainMenuOption();
            switch (option) {
                case VIEW_RESERVATIONS:
                    viewReservations();
                    break;
                case MAKE_RESERVATION:
//                    makeReservation();
                    break;
                case EDIT_RESERVATION:
//                    editReservation();
                    break;
                case CANCEL_RESERVATION:
//                    cancelReservation();
                    break;
            }
        } while (option != MainMenuOption.EXIT);
    }

    private void viewReservations() throws DataException {
        view.displayHeader(MainMenuOption.VIEW_RESERVATIONS.getMessage());
        String hostEmail = view.getHostEmail();
        Host host = hostService.findByHostEmail(hostEmail);
        List<Reservation> reservations = reservationService.findByHost(host);
        System.out.println();
        view.displayReservations(reservations);
        view.enterToContinue();
    }

//    private void makeReservation() throws DataException {
//        view.displayHeader(MainMenuOption.MAKE_RESERVATION.getMessage());
//        String hostEmail = view.getHostEmail();
//        Host host = hostService.findByHostEmail(hostEmail);
//        String guestEmail = view.getGuestEmail();
//        List<Guest> guest = guestService.findByGuestEmail(guestEmail);
//
//        List<Reservation> reservations = reservationService.findByHost(host);
//        view.displayReservations(reservations);
//
//
//        Reservation reservation = view.makeReservation(host, guest);
//        Result<Reservation> result = reservationService.add(reservation);
//        if (!result.isSuccess()) {
//            view.displayStatus(false, result.getErrorMessages());
//        } else {
//            String successMessage = String.format("Reservation %s created.", result.getPayload().getId());
//            view.displayStatus(true, successMessage);
//        }
//        view.enterToContinue();
//    }





//    private void editReservation() throws DataException {
//        view.displayHeader(MainMenuOption.EDIT_RESERVATION.getMessage());
//        String guestEmail = view.getGuestEmail();
//        String hostEmail = view.getHostEmail();
//        List<Reservation> reservations = reservationService.findByGuestEmailAndHostEmail(guestEmail, hostEmail);
//
//        view.enterToContinue();
//    }
//
//    private void cancelReservation() throws DataException {
//        view.displayHeader(MainMenuOption.CANCEL_RESERVATION.getMessage());
//        String guestEmail = view.getGuestEmail();
//        String hostEmail = view.getHostEmail();
//        List<Reservation> reservations = reservationService.findByGuestEmailAndHostEmail(guestEmail, hostEmail);
//        if (reservations.isEmpty()) {
//            view.displayMessage("No reservations found for guest email " + guestEmail +
//                    " and host email " + hostEmail);
//            view.enterToContinue();
//            return;
//        }
//        Result<Reservation> result = reservationService.deleteById();
//        view.confirmReservation("Please confirm cancellation.");
//        if (!result.isSuccess()) {
//            view.displayStatus(false, result.getErrorMessages());
//        } else {
//            String successMessage = String.format("Reservation %s created.", result.getPayload().getId());
//            view.displayStatus(true, successMessage);
//        }
//        view.enterToContinue();
//    }
//SUPPORT METHODS
//private Guest getGuest() throws DataException {
//    String guestEmail = view.getGuestEmail();
//    List<Guest> guests = guestService.findByGuestEmail(guestEmail);
//    return view.


//}
//     private Forager getForager() {
//        String lastNamePrefix = view.getForagerNamePrefix();
//        List<Forager> foragers = foragerService.findByLastName(lastNamePrefix);
//        return view.chooseForager(foragers);
//    }
//
//    private Host getHost() {
//
//    }
//     private Item getItem() {
//        Category category = view.getItemCategory();
//        List<Item> items = itemService.findByCategory(category);
//        return view.chooseItem(items);
//    }
}




//SUCCESS MESSAGES
