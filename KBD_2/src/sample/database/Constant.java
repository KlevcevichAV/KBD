package sample.database;

public class Constant {
    public static String EQUIPMENT = "equipment";
    public static String SELECT_ALL = "SELECT * FROM ";
    public static String SELECT_EQUIPMENT = "SELECT name, day(date_of_purchase), month(date_of_purchase), year(date_of_purchase), price FROM equipment";
    public static String INSERT = "INSERT INTO ";
    public static String VALUES_EQUIPMENT = "(inventory_number, name, date_of_purchase, price)";
    public static String VALUES = "VALUES (";
    public static String LEFT_BRACKET = "(";
    public static String RIGHT_BRACKET = ")";

    public static String intToDate(int day, int month, int year){
        String result = year + "-" + month + "-" + day;
        return result;
    }

}
