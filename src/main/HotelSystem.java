package main;

import java.util.*;
import main.model.*;
import main.util.*;

public class HotelSystem {
    private static List<Room> rooms;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        rooms = DataManager.loadRooms();
        int choice;

        do {
            System.out.println("\n=== Hotel Reservation System ===");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Room Booking");
            System.out.println("4. View All Bookings");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> viewAvailableRooms();
                case 2 -> bookRoom(sc);
                case 3 -> cancelBooking(sc);
                case 4 -> viewBookings();
                case 5 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice");
            }
        } while (choice != 5);
    }

    private static void viewAvailableRooms() {
        for (Room room : rooms) {
            if (!room.isBooked()) {
                System.out.println(room);
            }
        }
    }

    private static void bookRoom(Scanner sc) {
        System.out.print("Enter Guest Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine();

        System.out.print("Enter Room Category (Standard/Deluxe/Suite): ");
        String category = sc.nextLine();

        Room selected = null;
        for (Room room : rooms) {
            if (!room.isBooked() && room.getCategory().equalsIgnoreCase(category)) {
                selected = room;
                break;
            }
        }

        if (selected == null) {
            System.out.println("No available rooms in this category.");
            return;
        }

        double price = switch (selected.getCategory()) {
            case "Standard" -> 2000;
            case "Deluxe" -> 4000;
            case "Suite" -> 7000;
            default -> 0;
        };

        boolean paid = PaymentSimulator.processPayment(name, price);
        if (paid) {
            selected.setBooked(true);
            Guest guest = new Guest(name, phone);
            Booking booking = new Booking(guest, selected);
            DataManager.saveRooms(rooms);
            DataManager.saveBooking(booking);
            System.out.println("Room booked successfully!");
        }
    }

    private static void cancelBooking(Scanner sc) {
    System.out.print("Enter Room Number to cancel: ");
    int roomNum = sc.nextInt(); sc.nextLine();
    boolean found = false;

    for (Room room : rooms) {
        if (room.getRoomNumber() == roomNum && room.isBooked()) {
            room.setBooked(false);
            DataManager.saveRooms(rooms);
            DataManager.removeBookingByRoomNumber(roomNum);
            System.out.println("Booking cancelled.");
            found = true;
            break;
        }
    }
    if (!found) {
        System.out.println("No such booked room found.");
    }
}


    private static void viewBookings() {
        List<String> bookings = DataManager.loadBookings();
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (String booking : bookings) {
                System.out.println(booking);
            }
        }
    }
}