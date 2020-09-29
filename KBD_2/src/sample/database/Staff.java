package sample.database;

public class Staff {
    private String fullName;
    private String position;

    public String getFullName() {
        return '\'' + fullName + '\'';
    }

    public String getPosition() {
        return '\'' + position + '\'';
    }

    public Staff(String fullName, String position) {
        this.fullName = fullName;
        this.position = position;
    }
}
