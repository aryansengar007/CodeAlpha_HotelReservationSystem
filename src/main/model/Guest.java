package main.model;

public class Guest {
    private String name;
    private String phone;

    public Guest(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }
}