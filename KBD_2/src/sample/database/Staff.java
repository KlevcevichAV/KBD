package sample.database;

public class Staff {
    private String fullName;
    private String position;
    private String subdivisionName;

    public String getFullName() {
        return fullName;
    }

    public String getPosition() {
        return position;
    }

    public String getSubdivisionName(){
        return subdivisionName;
    }

    public Staff(String fullName, String position, String subdivisionName) {
        this.fullName = fullName;
        this.position = position;
        this.subdivisionName = subdivisionName;
    }
}
