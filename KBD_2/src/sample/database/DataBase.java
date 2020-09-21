package sample.database;

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

    private void settingProperties(){
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
            while (resultSet.next()){
                equipment.add(new Equipment(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), Integer.parseInt(resultSet.getString(3)), Integer.parseInt(resultSet.getString(4)),Integer.parseInt(resultSet.getString(5)), Integer.parseInt(resultSet.getString(6))));
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
            while (resultSet.next()){
                locationOfEquipments.add(new LocationOfEquipment(Integer.parseInt(resultSet.getString(1)), Integer.parseInt(resultSet.getString(2)), Integer.parseInt(resultSet.getString(3)),resultSet.getString(4), resultSet.getString(5), Integer.parseInt(resultSet.getString(6))));
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
            while (resultSet.next()){
                subdivisions.add(new Subdivision(Integer.parseInt(resultSet.getString(1)), resultSet.getString(2), resultSet.getString(3)));
            }
            System.out.println("We're created subdivisions.");
        }
    }

    public void addEquipment(Equipment added) throws SQLException {
        Connection connection = DriverManager.getConnection(url, p);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(Constant.INSERT + Constant.EQUIPMENT + Constant.VALUES_EQUIPMENT + Constant.VALUES +
                    Constant.LEFT_BRACKET + );
            equipment.add(added);
            System.out.println("We're added.");
        }
    }

    public DataBase() throws ClassNotFoundException, SQLException {
        setEquipment();
        setSubdivisions();
        setLocationOfEquipments();
        System.out.println("We're created database.");
    }

}
