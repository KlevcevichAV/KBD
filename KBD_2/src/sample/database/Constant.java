package sample.database;

public class Constant {
    public static String EQUIPMENT = "equipment";
    public static String LOCATION_OF_EQUIPMENT = "location_of_equipment";
    public static String SUBDIVISION = "subdivision";

    public static String SELECT_ALL = "SELECT * FROM ";
    public static String SELECT_EQUIPMENT = "SELECT name, day(date_of_purchase), month(date_of_purchase), year(date_of_purchase), price FROM equipment";

    public static String INSERT = "INSERT INTO ";

    public static String DELETE = "DELETE FROM ";

    public static String WHERE = " WHERE ";
    public static String AND = " AND ";

    public static String UPDATE = "UPDATE ";
    public static String SET = " SET ";

    public static String VALUES = "VALUES ";
    public static String VALUES_EQUIPMENT = "(inventory_number, name, date_of_purchase, price)";
    public static String INVENTORY_NUMBER = "inventory_number";
    public static String NAME = "name";
    public static String DATE_OF_PURCHASE = "date_of_purchase";
    public static String PRICE = "price";
    public static String VALUES_LOCATION_OF_EQUIPMENT = "(transmission_date, full_name, position, room_number)";
    public static String TRANSMISSION_DATE = "transmission_date";
    public static String FULL_NAME = "full_name";
    public static String POSITION = "position";
    public static String ROOM_NUMBER = "room_number";
    public static String VALUES_SUBDIVISION = "(number, full_name, short_name)";
    public static String NUMBER = "number";
    public static String SHORT_NAME = "short_name";

    public static String COMMA = ", ";
    public static String EQUAL = " = ";
    public static String SEMICOLON = ";";
    public static String LEFT_BRACKET = "(";
    public static String RIGHT_BRACKET = ")";

    public static String intToDate(int day, int month, int year){
        String result = year + "-" + month + "-" + day;
        return result;
    }

}
