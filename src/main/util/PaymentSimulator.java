package main.util;

public class PaymentSimulator {
    public static boolean processPayment(String guestName, double amount) {
        System.out.println("Processing payment of ₹" + amount + " for " + guestName + "...");
        System.out.println("Payment successful!");
        return true;
    }
}