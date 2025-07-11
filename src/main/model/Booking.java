package main.model;

public class Booking {
    private Guest guest;
    private Room room;

    public Booking(Guest guest, Room room) {
        this.guest = guest;
        this.room = room;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Booking: " + guest.getName() + " | Phone: " + guest.getPhone() +
               " | Room: " + room.getRoomNumber() + " (" + room.getCategory() + ")";
    }
}