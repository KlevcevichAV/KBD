package sample.database;

public class Technics {
    private int inventoryNumber;
    private String name;
    private int day;
    private int month;
    private int year;
    private String date;
    private double price;

    public int getInventoryNumber() {
        return inventoryNumber;
    }

    public String getName() {
        return '\'' + name + '\'';
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

    public Technics(int inventoryNumber, String name, int day, int month, int year, double price) {
        this.inventoryNumber = inventoryNumber;
        this.name = name;
        this.day = day;
        this.month = month;
        this.year = year;
        this.price = price;
        date = year + "-" + month + "-" + day;
    }
}
