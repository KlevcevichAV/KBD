package sample.database;

public class Technics {
    private int inventoryNumber;
    private String name;
    private int day;
    private int month;
    private int year;
    private String date;
    private double price;
    private String model;

    public int getInventoryNumber() {
        return inventoryNumber;
    }

    public String getName() {
        return name;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getModel() {
        return model;
    }

    public Technics(int inventoryNumber, String name, int day, int month, int year, double price, String model) {
        this.inventoryNumber = inventoryNumber;
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.price = price;
        this.model = model;
        date = year + "-" + month + "-" + day;
    }
}
