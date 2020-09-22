package sample.database;

public class Subdivision {
    private int number;
    private String fullName;
    private String shortName;

    public int getNumber() {
        return number;
    }

    public String getFullName() {
        return fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public Subdivision(int number, String fullName, String shortName){
        this.number = number;
        this.fullName = fullName;
        this.shortName = shortName;
    }
}
