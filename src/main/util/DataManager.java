package main.util;

import java.io.*;
import java.util.*;
import main.model.*;

public class DataManager {
    private static final String ROOMS_FILE = "data/rooms.txt";
    private static final String BOOKINGS_FILE = "data/bookings.txt";

    public static List<Room> loadRooms() {
        List<Room> rooms = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(ROOMS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int number = Integer.parseInt(parts[0]);
                String category = parts[1];
                boolean isBooked = Boolean.parseBoolean(parts[2]);
                rooms.add(new Room(number, category, isBooked));
            }
        } catch (IOException e) {
            System.out.println("Error reading rooms.txt");
        }
        return rooms;
    }

    public static void saveRooms(List<Room> rooms) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(ROOMS_FILE))) {
            for (Room r : rooms) {
                pw.println(r.getRoomNumber() + "," + r.getCategory() + "," + r.isBooked());
            }
        } catch (IOException e) {
            System.out.println("Error writing rooms.txt");
        }
    }
    public static void removeBookingByRoomNumber(int roomNumber) {
    List<String> bookings = loadBookings();
    List<String> updated = new ArrayList<>();

    for (String booking : bookings) {
        String[] parts = booking.split(",");
        int bookedRoom = Integer.parseInt(parts[2]);
        if (bookedRoom != roomNumber) {
            updated.add(booking);
        }
    }

    try (PrintWriter pw = new PrintWriter(new FileWriter(BOOKINGS_FILE))) {
        for (String line : updated) {
            pw.println(line);
        }
    } catch (IOException e) {
        System.out.println("Error updating bookings.txt");
    }
}


    public static void saveBooking(Booking booking) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(BOOKINGS_FILE, true))) {
            pw.println(booking.getGuest().getName() + "," +
                       booking.getGuest().getPhone() + "," +
                       booking.getRoom().getRoomNumber() + "," +
                       booking.getRoom().getCategory());
        } catch (IOException e) {
            System.out.println("Error writing bookings.txt");
        }
    }

    public static List<String> loadBookings() {
        List<String> bookings = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(BOOKINGS_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                bookings.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading bookings.txt");
        }
        return bookings;
    }
}