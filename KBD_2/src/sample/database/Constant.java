package sample.database;

public class Constant {
    public static final String TECHNICS = "technics";
    public static final String TRANSFER = "transfer";
    public static final String SUBDIVISION = "subdivision";
    public static final String STAFF = "staff";

    public static final String SELECT_TECHNICS = "SELECT inventory_number, name, day(date_of_purchase), month(date_of_purchase), year(date_of_purchase), price FROM technics";
    public static final String SELECT_TRANSFER = "SELECT day(transmission_date), month(transmission_date), year(transmission_date), full_name, room_number, inventory_number FROM transfer";
    public static final String SELECT_SUBDIVISION = "SELECT number, full_name, short_name FROM subdivision";
    public static final String SELECT_STAFF = "SELECT * FROM staff";
    public static final String SELECT_LIST_TECHNICS_SUBDIVISION = "SELECT technics.inventory_number, technics.name FROM technics";
    public static final String SELECT_LIST_LIST_ROOM = "SELECT transfer.room_number FROM technics";

    public static final String LEFT_JOIN = "LEFT JOIN ";
    public static final String ON = " ON ";


    public static final String INSERT = "INSERT INTO ";

    public static final String DELETE = "DELETE FROM ";

    public static final String WHERE = " WHERE ";
    public static final String AND = " AND ";

    public static final String UPDATE = "UPDATE ";
    public static final String SET = " SET ";

    public static final String VALUES = "VALUES ";
    public static final String VALUES_TECHNICS = "(inventory_number, name, date_of_purchase, price)";
    public static final String INVENTORY_NUMBER = "inventory_number";
    public static final String NAME = "name";
    public static final String DATE_OF_PURCHASE = "date_of_purchase";
    public static final String PRICE = "price";
    public static final String VALUES_TRANSFER = "(transmission_date, full_name, room_number, inventory_number)";
    public static final String TRANSMISSION_DATE = "transmission_date";
    public static final String FULL_NAME = "full_name";
    public static final String ROOM_NUMBER = "room_number";
    public static final String INVENTORY_NUMBER_TRANSFER = "inventory_number";
    public static final String VALUES_SUBDIVISION = "(number, full_name, short_name)";
    public static final String NUMBER = "number";
    public static final String SHORT_NAME = "short_name";
    public static final String VALUES_STAFF = "(full_name, position, subdivision_name)";
    public static final String POSITION = "position";
    public static final String SUBDIVISION_NAME = "subdivision_name";

    public static final String INVENTORY_NUMBER_TEC = "technics.inventory_number";
    public static final String INVENTORY_NUMBER_TRA = "transfer.inventory_number";
    public static final String TRANSFER_FN = "transfer.full_name";
    public static final String FN_SUBDIVISION = "subdivision.full_name";
    public static final String SUBDIVISION_NAME_STAFF = "staff.subdivision_name";
    public static final String DATE_TRANSFER = "transfer.transmission_date";
    public static final String FN_STAFF = "staff.full_name";

    public static final String COMMA = ", ";
    public static final String EQUAL = " = ";
    public static final String SEMICOLON = ";";
    public static final String LEFT_BRACKET = "(";
    public static final String RIGHT_BRACKET = ")";

    public static final String LEFT_JOIN_TECHNIC_TRANSFER = " " + LEFT_JOIN + TRANSFER + ON + INVENTORY_NUMBER_TEC + EQUAL + INVENTORY_NUMBER_TRA;
    public static final String LEFT_JOIN_TRANSFER_STAFF = " " + LEFT_JOIN + STAFF + ON + TRANSFER_FN + EQUAL + FN_STAFF;
    public static final String LEFT_JOIN_SUBDIVISION_STAFF = " " + LEFT_JOIN + SUBDIVISION + ON + FN_SUBDIVISION + EQUAL + SUBDIVISION_NAME_STAFF;

    public static String intToDate(int day, int month, int year) {
        String result = year + "-" + month + "-" + day;
        return result;
    }

}
