package sample.database;
//start debug :)(

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBase {
    private String url = "jdbc:mysql://localhost:3306/KBD_2?useSSL=false";
    private Properties p;

    private List<Technics> technics;
    private List<Transfer> transfers;
    private List<Subdivision> subdivisions;
    private List<Staff> staff;

    public List<Technics> getTechnics() {
        return technics;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public List<Subdivision> getSubdivisions() {
        return subdivisions;
    }

    private String whereTechnics(Technics technics) {
        String result = Constant.WHERE +
                Constant.INVENTORY_NUMBER + Constant.EQUAL + technics.getInventoryNumber() + Constant.AND +
                Constant.NAME + Constant.EQUAL + addAp(technics.getName()) + Constant.AND +
                Constant.DATE_OF_PURCHASE + Constant.EQUAL + addAp(Constant.intToDate(technics.getDay(), technics.getMonth(), technics.getYear())) + Constant.AND +
                Constant.PRICE + Constant.EQUAL + technics.getPrice();
        return result;
    }

    private String whereTransfers(Transfer Transfers) {
        String result = Constant.WHERE +
                Constant.TRANSMISSION_DATE + Constant.EQUAL + Constant.intToDate(Transfers.getDay(), Transfers.getMonth(), Transfers.getYear()) + Constant.AND +
                Constant.FULL_NAME + Constant.EQUAL + Transfers.getFullName() + Constant.AND +
                Constant.ROOM_NUMBER + Constant.EQUAL + Transfers.getRoomNumber();
        return result;
    }

    private String whereSubdivisions(Subdivision subdivision) {
        String result = Constant.WHERE +
                Constant.NUMBER + Constant.EQUAL + subdivision.getNumber() + Constant.AND +
                Constant.FULL_NAME + Constant.EQUAL + addAp(subdivision.getFullName()) + Constant.AND +
                Constant.SHORT_NAME + Constant.EQUAL + addAp(subdivision.getShortName());
        return result;
    }

    private String whereStaff(Staff staff) {
        String result = Constant.WHERE +
                Constant.FULL_NAME + Constant.EQUAL + staff.getFullName() + Constant.AND +
                Constant.POSITION + Constant.EQUAL + staff.getPosition();
        return result;
    }

    private void settingProperties() {
        p = new Properties();
        p.setProperty("user", "root");
        p.setProperty("password", "123");
        p.setProperty("useUnicode", "true");
        p.setProperty("characterEncoding", "cp1251");
    }

    private void setTechnics() throws ClassNotFoundException, SQLException {
        technics = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Constant.SELECT_TECHNICS);
            while (resultSet.next()) {
                technics.add(new Technics(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), Integer.parseInt(resultSet.getString(3)), Integer.parseInt(resultSet.getString(4)), Integer.parseInt(resultSet.getString(5)), Integer.parseInt(resultSet.getString(6))));
            }
            System.out.println("We're created Technics.");
        }
    }

    private void setTransfers() throws ClassNotFoundException, SQLException {
        transfers = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Constant.SELECT_TRANSFER);
            while (resultSet.next()) {
                transfers.add(new Transfer(Integer.parseInt(resultSet.getString(1)), Integer.parseInt(resultSet.getString(2)), Integer.parseInt(resultSet.getString(3)), resultSet.getString(4), Integer.parseInt(resultSet.getString(5))));
            }
            System.out.println("We're created location Of Technicss.");
        }
    }

    private void setSubdivisions() throws ClassNotFoundException, SQLException {
        subdivisions = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Constant.SELECT_SUBDIVISION);
            while (resultSet.next()) {
                subdivisions.add(new Subdivision(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3)));
            }
            System.out.println("We're created subdivisions.");
        }
    }

    private void setStaff() throws ClassNotFoundException, SQLException {
        staff = new ArrayList<>();
        Class.forName("com.mysql.jdbc.Driver");
        settingProperties();
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(Constant.SELECT_STAFF);
            while (resultSet.next()) {
                staff.add(new Staff(resultSet.getString(1), resultSet.getString(2)));
            }
            System.out.println("We're created subdivisions.");
        }
    }

    public void addTechnics(Technics added) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            String eq = Constant.INSERT + Constant.TECHNICS + Constant.VALUES_TECHNICS +
                    Constant.VALUES + Constant.LEFT_BRACKET +
                    added.getInventoryNumber() + Constant.COMMA +
                    addAp(added.getName()) + Constant.COMMA +
                    addAp(Constant.intToDate(added.getDay(), added.getMonth(), added.getYear())) + Constant.COMMA +
                    added.getPrice() + Constant.RIGHT_BRACKET + Constant.SEMICOLON;
            statement.executeUpdate(eq);
            technics.add(added);
            System.out.println("We're added.");
        }
    }

    public void addTransfers(Transfer added) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.INSERT + Constant.STAFF + Constant.VALUES_STAFF +
                    Constant.VALUES + Constant.LEFT_BRACKET +
                    Constant.intToDate(added.getDay(), added.getMonth(), added.getYear()) + Constant.COMMA +
                    added.getFullName() + Constant.COMMA +
                    added.getRoomNumber() + Constant.RIGHT_BRACKET + Constant.SEMICOLON);
            transfers.add(added);
            System.out.println("We're added.");
        }
    }

    private String addAp(String string) {
        return '\'' + string + '\'';
    }

    public void addSubdivisions(Subdivision added) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            String eq = Constant.INSERT + Constant.SUBDIVISION + Constant.VALUES_SUBDIVISION +
                    Constant.VALUES + Constant.LEFT_BRACKET +
                    added.getNumber() + Constant.COMMA +
                    addAp(added.getFullName()) + Constant.COMMA +
                    addAp(added.getShortName()) + Constant.RIGHT_BRACKET + Constant.SEMICOLON;
            statement.executeUpdate(eq);
            subdivisions.add(added);
            System.out.println("We're added.");
        }
    }

    public void addStaff(Staff added) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.INSERT + Constant.STAFF + Constant.VALUES_SUBDIVISION +
                    Constant.VALUES + Constant.LEFT_BRACKET +
                    added.getFullName() + Constant.COMMA +
                    added.getPosition() + Constant.RIGHT_BRACKET + Constant.SEMICOLON);
            staff.add(added);
            System.out.println("We're added.");
        }
    }

    public void deleteTechnics(Technics deleted) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            String eq = Constant.DELETE + Constant.TECHNICS + whereTechnics(deleted) + Constant.SEMICOLON;
            statement.executeUpdate(eq);
            for(int i = 0; i < technics.size(); i++){
                if(technics.get(i) == deleted){
                    technics.remove(i);
                }
            }
        }
    }

    public void deleteTransfers(Transfer deleted) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.DELETE + Constant.STAFF + whereTransfers(deleted) + Constant.SEMICOLON);
        }
    }

    public void deleteSubdivisions(Subdivision deleted) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.DELETE + Constant.SUBDIVISION + whereSubdivisions(deleted) + Constant.SEMICOLON);
            for(int i = 0; i < subdivisions.size(); i++){
                if(subdivisions.get(i) == deleted){
                    subdivisions.remove(i);
                }
            }
        }
    }

    public void deleteStaff(Staff deleted) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.DELETE + Constant.STAFF + whereStaff(deleted) + Constant.SEMICOLON);
        }
    }

    public void editTechnics(Technics edited, Technics newTechnics) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.UPDATE + Constant.TECHNICS + Constant.SET +
                    Constant.INVENTORY_NUMBER + Constant.EQUAL + newTechnics.getInventoryNumber() + Constant.COMMA +
                    Constant.NAME + Constant.EQUAL + addAp(newTechnics.getName()) + Constant.COMMA +
                    Constant.DATE_OF_PURCHASE + Constant.EQUAL + addAp(Constant.intToDate(newTechnics.getDay(), newTechnics.getMonth(), newTechnics.getYear())) + Constant.COMMA +
                    Constant.PRICE + Constant.EQUAL + newTechnics.getPrice() + whereTechnics(edited) + Constant.SEMICOLON
            );
            for(int i = 0; i < subdivisions.size(); i++){
                if(technics.get(i) == edited){
                    technics.set(i, newTechnics);
                }
            }
        }
    }

    public void editTransfers(Transfer edited, Transfer newTransfer) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.UPDATE + Constant.STAFF + Constant.SET +
                    Constant.TRANSMISSION_DATE + Constant.EQUAL + Constant.intToDate(newTransfer.getDay(), newTransfer.getMonth(), newTransfer.getYear()) + Constant.COMMA +
                    Constant.FULL_NAME + Constant.EQUAL + newTransfer.getFullName() + Constant.COMMA +
                    Constant.ROOM_NUMBER + Constant.EQUAL + newTransfer.getRoomNumber() + whereTransfers(edited) + Constant.SEMICOLON
            );
        }
    }

    public void editSubdivisions(Subdivision edited, Subdivision newSubdivision) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            String eq = Constant.UPDATE + Constant.SUBDIVISION + Constant.SET +
                    Constant.NUMBER + Constant.EQUAL + newSubdivision.getNumber() + Constant.COMMA +
                    Constant.FULL_NAME + Constant.EQUAL + addAp(newSubdivision.getFullName()) + Constant.COMMA +
                    Constant.SHORT_NAME + Constant.EQUAL + addAp(newSubdivision.getShortName()) + whereSubdivisions(edited) + Constant.SEMICOLON;
            statement.executeUpdate(eq);
            for(int i = 0; i < subdivisions.size(); i++){
                if(subdivisions.get(i) == edited){
                    subdivisions.set(i, newSubdivision);
                    break;
                }
            }
        }
    }

    public void editStaff(Staff edited, Staff newStaff) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.UPDATE + Constant.TECHNICS + Constant.SET +
                    Constant.FULL_NAME + Constant.EQUAL + newStaff.getFullName() + Constant.COMMA +
                    Constant.POSITION + Constant.EQUAL + newStaff.getPosition() + whereStaff(edited) + Constant.SEMICOLON
            );
        }
    }

    public DataBase() throws ClassNotFoundException, SQLException {
        setTechnics();
        setSubdivisions();
        setTransfers();
        setStaff();
        System.out.println("We're created database.");
    }

}
