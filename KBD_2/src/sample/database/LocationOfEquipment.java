package sample.database;

public class LocationOfEquipment {
    private int day;
    private int month;
    private int year;
    private String fullName;
    private String position;
    private int roomNumber;

    public LocationOfEquipment(int day, int month, int year, String fullName, String position, int roomNumber){
        this.day = day;
        this.month = month;
        this.year = year;
        this.fullName = fullName;
        this.position = position;
        this.roomNumber = roomNumber;
    }
}
