package sample.database;
//add apostrophes for string getters
//start debug :)(
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBase {
    private String url = "jdbc:mysql://localhost:3306/vocabulary?useSSL=false";
    private Properties p;

    private List<Equipment> equipment;
    private List<LocationOfEquipment> locationOfEquipments;
    private List<Subdivision> subdivisions;

    private String whereEquipment(Equipment equipment) {
        String result = Constant.WHERE +
                Constant.INVENTORY_NUMBER + Constant.EQUAL + equipment.getInventoryNumber() + Constant.AND +
                Constant.NAME + Constant.EQUAL + equipment.getName() + Constant.AND +
                Constant.DATE_OF_PURCHASE + Constant.EQUAL + Constant.intToDate(equipment.getDay(), equipment.getMonth(), equipment.getYear()) + Constant.AND +
                Constant.PRICE + Constant.EQUAL + equipment.getPrice();
        return result;
    }

    private String whereLocationOfEquipments(LocationOfEquipment locationOfEquipments) {
        String result = Constant.WHERE +
                Constant.TRANSMISSION_DATE + Constant.EQUAL + Constant.intToDate(locationOfEquipments.getDay(), locationOfEquipments.getMonth(), locationOfEquipments.getYear()) + Constant.AND +
                Constant.FULL_NAME + Constant.EQUAL + locationOfEquipments.getFullName() + Constant.AND +
                Constant.POSITION + Constant.EQUAL + locationOfEquipments.getPosition() + Constant.AND +
                Constant.ROOM_NUMBER + Constant.EQUAL + locationOfEquipments.getRoomNumber();
        return result;
    }

    private String whereSubdivisions(Subdivision subdivision) {
        String result = Constant.WHERE +
                Constant.NUMBER + Constant.EQUAL + subdivision.getNumber() + Constant.AND +
                Constant.FULL_NAME + Constant.EQUAL + subdivision.getFullName() + Constant.AND +
                Constant.SHORT_NAME + Constant.EQUAL + subdivision.getShortName();
        return result;
    }

    private void settingProperties() {
        p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "123");
        p.setProperty("useUnicode", "true");
        p.setProperty("characterEncoding", "cp1251");
    }

    private void setEquipment() throws ClassNotFoundException, SQLException {
        equipment = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Constant.SELECT_EQUIPMENT);
            while (resultSet.next()) {
                equipment.add(new Equipment(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), Integer.parseInt(resultSet.getString(3)), Integer.parseInt(resultSet.getString(4)), Integer.parseInt(resultSet.getString(5)), Integer.parseInt(resultSet.getString(6))));
            }
            System.out.println("We're created equipment.");
        }
    }

    private void setLocationOfEquipments() throws ClassNotFoundException, SQLException {
        locationOfEquipments = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Constant.SELECT_EQUIPMENT);
            while (resultSet.next()) {
                locationOfEquipments.add(new LocationOfEquipment(Integer.parseInt(resultSet.getString(1)), Integer.parseInt(resultSet.getString(2)), Integer.parseInt(resultSet.getString(3)), resultSet.getString(4), resultSet.getString(5), Integer.parseInt(resultSet.getString(6))));
            }
            System.out.println("We're created location Of Equipments.");
        }
    }

    private void setSubdivisions() throws ClassNotFoundException, SQLException {
        subdivisions = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Constant.SELECT_EQUIPMENT);
            while (resultSet.next()) {
                subdivisions.add(new Subdivision(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3)));
            }
            System.out.println("We're created subdivisions.");
        }
    }

    public void addEquipment(Equipment added) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.INSERT + Constant.EQUIPMENT + Constant.VALUES_EQUIPMENT +
                    Constant.VALUES + Constant.LEFT_BRACKET +
                    added.getInventoryNumber() + Constant.COMMA +
                    added.getName() + Constant.COMMA +
                    Constant.intToDate(added.getDay(), added.getMonth(), added.getYear()) + Constant.COMMA +
                    added.getPrice() + Constant.RIGHT_BRACKET + Constant.SEMICOLON);
            equipment.add(added);
            System.out.println("We're added.");
        }
    }

    public void addLocationOfEquipments(LocationOfEquipment added) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.INSERT + Constant.LOCATION_OF_EQUIPMENT + Constant.VALUES_LOCATION_OF_EQUIPMENT +
                    Constant.VALUES + Constant.LEFT_BRACKET +
                    Constant.intToDate(added.getDay(), added.getMonth(), added.getYear()) + Constant.COMMA +
                    added.getFullName() + Constant.COMMA +
                    added.getPosition() + Constant.COMMA +
                    added.getRoomNumber() + Constant.RIGHT_BRACKET + Constant.SEMICOLON);
            locationOfEquipments.add(added);
            System.out.println("We're added.");
        }
    }

    public void addSubdivisions(Subdivision added) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.INSERT + Constant.SUBDIVISION + Constant.VALUES_SUBDIVISION +
                    Constant.VALUES + Constant.LEFT_BRACKET +
                    added.getNumber() + Constant.COMMA +
                    added.getFullName() + Constant.COMMA +
                    added.getShortName() + Constant.RIGHT_BRACKET + Constant.SEMICOLON);
            subdivisions.add(added);
            System.out.println("We're added.");
        }
    }

    public void deleteEquipment(Equipment deleted) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.DELETE + Constant.EQUIPMENT + whereEquipment(deleted) + Constant.SEMICOLON);
        }
    }

    public void deleteLocationOfEquipments(LocationOfEquipment deleted) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.DELETE + Constant.LOCATION_OF_EQUIPMENT + whereLocationOfEquipments(deleted) + Constant.SEMICOLON);
        }
    }

    public void deleteSubdivisions(Subdivision deleted) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.DELETE + Constant.SUBDIVISION + whereSubdivisions(deleted) + Constant.SEMICOLON);
        }
    }

    public void editEquipment(Equipment edited) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.UPDATE + Constant.EQUIPMENT + Constant.SET +
                    Constant.INVENTORY_NUMBER + Constant.EQUAL + edited.getInventoryNumber() + Constant.COMMA +
                    Constant.NAME + Constant.EQUAL + edited.getName() + Constant.COMMA +
                    Constant.DATE_OF_PURCHASE + Constant.EQUAL + Constant.intToDate(edited.getDay(), edited.getMonth(), edited.getYear()) + Constant.COMMA +
                    Constant.PRICE + Constant.EQUAL + edited.getPrice() + whereEquipment(edited) + Constant.SEMICOLON
            );
        }
    }

    public void editLocationOfEquipments(LocationOfEquipment edited) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.UPDATE + Constant.LOCATION_OF_EQUIPMENT + Constant.SET +
                    Constant.TRANSMISSION_DATE + Constant.EQUAL + Constant.intToDate(edited.getDay(), edited.getMonth(), edited.getYear()) + Constant.COMMA +
                    Constant.FULL_NAME + Constant.EQUAL + edited.getFullName() + Constant.COMMA +
                    Constant.POSITION + Constant.EQUAL + edited.getPosition() + Constant.COMMA +
                    Constant.ROOM_NUMBER + Constant.EQUAL + edited.getRoomNumber() + whereLocationOfEquipments(edited) + Constant.SEMICOLON
            );
        }
    }

    public void editSubdivisions(Subdivision edited) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.UPDATE + Constant.EQUIPMENT + Constant.SET +
                    Constant.NUMBER + Constant.EQUAL + edited.getNumber() + Constant.COMMA +
                    Constant.FULL_NAME + Constant.EQUAL + edited.getFullName() + Constant.COMMA +
                    Constant.SHORT_NAME + Constant.EQUAL + edited.getShortName() + whereSubdivisions(edited) + Constant.SEMICOLON
            );
        }
    }

    public DataBase() throws ClassNotFoundException, SQLException {
        setEquipment();
        setSubdivisions();
        setLocationOfEquipments();
        System.out.println("We're created database.");
    }

}
