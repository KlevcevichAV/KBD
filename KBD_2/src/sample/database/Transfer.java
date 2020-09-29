package sample.database;

public class Transfer {
    private int day;
    private int month;
    private int year;
    private String fullName;
    private int roomNumber;

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
        return '\'' + fullName + '\'';
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public Transfer(int day, int month, int year, String fullName, int roomNumber) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.fullName = fullName;
        this.roomNumber = roomNumber;
    }
}
