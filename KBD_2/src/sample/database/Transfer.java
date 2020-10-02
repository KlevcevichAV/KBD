package sample.database;

public class Transfer {
    private int day;
    private int month;
    private int year;
    private String fullName;
    private int roomNumber;
    private String date;
    private int inventoryNumber;

    public String getDate() {
        return date;
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

    public String getFullName() {
        return fullName;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getInventoryNumber() {
        return inventoryNumber;
    }

    public Transfer(int day, int month, int year, String fullName, int roomNumber, int inventoryNumber) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.fullName = fullName;
        this.roomNumber = roomNumber;
        this.inventoryNumber = inventoryNumber;
        date = year + "-" + month + "-" + day;
    }
}
