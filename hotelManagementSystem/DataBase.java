package hotelManagementSystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DataBase {

    private String database_location_string = "jdbc:sqlite:C:{ABSOLUTE DATABASE LOCATION}\\hotel.db";

    public static final String TABLE_USERINFO = "userInfo";
    public static final String COLUMN_USERINFO_ID = "ID";
    public static final String COLUMN_USERINFO_USERNAME = "userName";
    public static final String COLUMN_USERINFO_PASSWORD = "password";
    public static final String COLUMN_USERINFO_FULLNAME = "fullName";
    public static final String COLUMN_USERINFO_ADDRESS = "address";
    public static final String COLUMN_USERINFO_PHONE = "phone";
    public static final String COLUMN_USERINFO_STARTDATE = "startDate";
    public static final String COLUMN_USERINFO_SALARY = "salary";
    public static final String COLUMN_USERINFO_USERTYPE = "userType";
    public static final int INDEX_USERINFO_ID = 1;
    public static final int INDEX_USERINFO_USERNAME = 2;
    public static final int INDEX_USERINFO_PASSWORD = 3;
    public static final int INDEX_USERINFO_FULLNAME = 4;
    public static final int INDEX_USERINFO_ADDRESS = 5;
    public static final int INDEX_USERINFO_PHONE = 6;
    public static final int INDEX_USERINFO_STARTDATE = 7;
    public static final int INDEX_USERINFO_SALARY = 8;
    public static final int INDEX_USERINFO_USERTYPE = 9;

    public static final String TABLE_ROOMDETAILS = "RoomDetails";
    public static final String COLUMN_ROOMDETAILS_ROOMNO = "roomNo";
    public static final String COLUMN_ROOMDETAILS_ROOMTYPE = "roomType";
    public static final String COLUMN_ROOMDETAILS_MAXPEOPLE = "maxPeople";
    public static final String COLUMN_ROOMDETAILS_FLOORNO = "floorNo";
    public static final String COLUMN_ROOMDETAILS_PHONENO = "phoneNo";
    public static final String COLUMN_ROOMDETAILS_RATE = "rate";
    public static final String COLUMN_ROOMDETAILS_STATUS = "status";
    public static final int INDEX_ROOMDETAILS_ROOMNO = 1;
    public static final int INDEX_ROOMDETAILS_ROOMTYPE = 2;
    public static final int INDEX_ROOMDETAILS_MAXPEOPLE = 3;
    public static final int INDEX_ROOMDETAILS_FLOORNO = 4;
    public static final int INDEX_ROOMDETAILS_PHONENO = 5;
    public static final int INDEX_ROOMDETAILS_RATE = 6;
    public static final int INDEX_ROOMDETAILS_STATUS = 7;

    public static final String TABLE_RESERVATIONS = "reservations";
    public static final String COLUMN_RESERVATIONS_ID = "id";
    public static final String COLUMN_RESERVATIONS_NAME = "name";
    public static final String COLUMN_RESERVATIONS_PHONENO = "phoneNo";
    public static final String COLUMN_RESERVATIONS_ADDRESS = "address";
    public static final String COLUMN_RESERVATIONS_EMAIL = "email";
    public static final String COLUMN_RESERVATIONS_DURATION = "duration";
    public static final String COLUMN_RESERVATIONS_NOOFPEOPLE = "noOfPeople";
    public static final String COLUMN_RESERVATIONS_PAYMENTMETHOD = "paymentMethod";
    public static final String COLUMN_RESERVATIONS_ROOMTYPE = "roomType";
    public static final String COLUMN_RESERVATIONS_ROOMNO = "roomNo";
    public static final String COLUMN_RESERVATIONS_ARRIVALDATE = "arrivalDate";
    public static final String COLUMN_RESERVATIONS_DEPARTUREDATE = "departureDate";
    public static final String COLUMN_RESERVATIONS_PRICEPERNIGHT = "pricePerNight";
    public static final String COLUMN_RESERVATIONS_SERVICES = "services";
    public static final String COLUMN_RESERVATIONS_TOTAL = "total";
    public static final int INDEX_RESERVATIONS_ID = 1;
    public static final int INDEX_RESERVATIONS_NAME = 2;
    public static final int INDEX_RESERVATIONS_PHONENO = 3;
    public static final int INDEX_RESERVATIONS_ADDRESS = 4;
    public static final int INDEX_RESERVATIONS_EMAIL = 5;
    public static final int INDEX_RESERVATIONS_DURATION = 6;
    public static final int INDEX_RESERVATIONS_NOOFPEOPLE = 7;
    public static final int INDEX_RESERVATIONS_PAYMENTMETHOD = 8;
    public static final int INDEX_RESERVATIONS_ROOMTYPE = 9;
    public static final int INDEX_RESERVATIONS_ROOMNO = 10;
    public static final int INDEX_RESERVATIONS_ARRIVALDATE = 11;
    public static final int INDEX_RESERVATIONS_DEPARTUREDATE = 12;
    public static final int INDEX_RESERVATIONS_PRICEPERNIGHT = 13;
    public static final int INDEX_RESERVATIONS_SERVICES = 14;
    public static final int INDEX_RESERVATIONS_TOTAL = 15;

    private ObservableList<Room> rooms;

    private ObservableList<Reservation> reservations;

    private ObservableList<User> users;



    public DataBase() {

        rooms = FXCollections.observableArrayList();
        reservations = FXCollections.observableArrayList();
        users = FXCollections.observableArrayList();

    }

    public ObservableList<Room> getRooms() {
        return rooms;
    }

    public ObservableList<Reservation> getReservations() {
        return reservations;
    }

    public ObservableList<User> getUsers() {
        return users;
    }

    private Connection conn;

    public boolean openConnection () {
        try {
            String CONNECTION_STRING = database_location_string;
            conn = DriverManager.getConnection(CONNECTION_STRING);
//            System.out.println("Connected to Database");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database : " + e.getMessage());
            return false;
        }
    }

    public boolean closeConnection () {
        try {
            conn.close();
//            System.out.println("Database Connection close");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't close connection : " + e.getMessage());
            return false;
        }
    }

    public boolean addNewUser (User user) {

        try {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO " + TABLE_USERINFO +
                                        " (" + COLUMN_USERINFO_ID + ", " +
                                                COLUMN_USERINFO_USERNAME + ", " +
                                                COLUMN_USERINFO_PASSWORD + ", " +
                                                COLUMN_USERINFO_FULLNAME + ", " +
                                                COLUMN_USERINFO_ADDRESS + ", " +
                                                COLUMN_USERINFO_PHONE + ", " +
                                                COLUMN_USERINFO_STARTDATE + ", " +
                                                COLUMN_USERINFO_SALARY + ", " +
                                                COLUMN_USERINFO_USERTYPE + " ) " +
                                        "VALUES('" + user.getId() + "', '" + user.getUsername() + "', '" + user.getPassword() + "', '" + user.getFullname() + "', '" +
                                                user.getAddress() + "', '" + user.getPhone() + "', '" + user.getStartDate() + "', '" +
                                                user.getSalary() + "', '" + user.getUserType() + "')");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't add user : " + e.getMessage());
            return false;
        }
    }

    public void loadUsers () {

        try (Statement statement = conn.createStatement();
                ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_USERINFO)){

            while (results.next()) {

                User user = new User();
                user.setId(results.getString(INDEX_USERINFO_ID));
                user.setUsername(results.getString(INDEX_USERINFO_USERNAME));
                user.setPassword(results.getString(INDEX_USERINFO_PASSWORD));
                user.setFullname(results.getString(INDEX_USERINFO_FULLNAME));
                user.setAddress(results.getString(INDEX_USERINFO_ADDRESS));
                user.setPhone(results.getString(INDEX_USERINFO_PHONE));
                user.setStartDate(results.getString(INDEX_USERINFO_STARTDATE));
                user.setSalary(results.getString(INDEX_USERINFO_SALARY));
                user.setUserType(results.getString(INDEX_USERINFO_USERTYPE));
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Query Failed.");
        }
    }

    public boolean addNewReservation (Reservation reservation) {

        try {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO " + TABLE_RESERVATIONS +
                    " (" + COLUMN_RESERVATIONS_ID + ", " +
                    COLUMN_RESERVATIONS_NAME + ", " +
                    COLUMN_RESERVATIONS_PHONENO + ", " +
                    COLUMN_RESERVATIONS_ADDRESS + ", " +
                    COLUMN_RESERVATIONS_EMAIL + ", " +
                    COLUMN_RESERVATIONS_DURATION + ", " +
                    COLUMN_RESERVATIONS_NOOFPEOPLE + ", " +
                    COLUMN_RESERVATIONS_PAYMENTMETHOD + ", " +
                    COLUMN_RESERVATIONS_ROOMTYPE + ", " +
                    COLUMN_RESERVATIONS_ROOMNO + ", " +
                    COLUMN_RESERVATIONS_ARRIVALDATE + ", " +
                    COLUMN_RESERVATIONS_DEPARTUREDATE + ", " +
                    COLUMN_RESERVATIONS_PRICEPERNIGHT + ", " +
                    COLUMN_RESERVATIONS_SERVICES + ", " +
                    COLUMN_RESERVATIONS_TOTAL + " ) " +
                    "VALUES('" + reservation.getId() + "', '" + reservation.getName() + "', '" + reservation.getPhoneNo() + "', '" +
                    reservation.getAddress() + "', '" + reservation.getEmail() + "', '" + reservation.getDuration() + "', '" +
                    reservation.getNoOfPeople() + "', '" + reservation.getPaymentMethod() + "', '" + reservation.getRoomType() + "', '" +
                    reservation.getRoomNo() + "', '" + reservation.getArrivalDate() + "', '" + reservation.getDepartureDate() + "', '" +
                    reservation.getPricePerNight() + "', '" + reservation.getServices() + "', '" + reservation.getTotal() + "')");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't add Reservation : " + e.getMessage());
            return false;
        }
    }

    public void loadReservations () {

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_RESERVATIONS)){

            while (results.next()) {

                Reservation reservation = new Reservation();
                reservation.setId(results.getString(INDEX_RESERVATIONS_ID));
                reservation.setName(results.getString(INDEX_RESERVATIONS_NAME));
                reservation.setPhoneNo(results.getString(INDEX_RESERVATIONS_PHONENO));
                reservation.setAddress(results.getString(INDEX_RESERVATIONS_ADDRESS));
                reservation.setEmail(results.getString(INDEX_RESERVATIONS_EMAIL));
                reservation.setDuration(results.getString(INDEX_RESERVATIONS_DURATION));
                reservation.setNoOfPeople(results.getString(INDEX_RESERVATIONS_NOOFPEOPLE));
                reservation.setPaymentMethod(results.getString(INDEX_RESERVATIONS_PAYMENTMETHOD));
                reservation.setRoomType(results.getString(INDEX_RESERVATIONS_ROOMTYPE));
                reservation.setRoomNo(results.getString(INDEX_RESERVATIONS_ROOMNO));
                reservation.setArrivalDate(results.getString(INDEX_RESERVATIONS_ARRIVALDATE));
                reservation.setDepartureDate(results.getString(INDEX_RESERVATIONS_DEPARTUREDATE));
                reservation.setPricePerNight(results.getString(INDEX_RESERVATIONS_PRICEPERNIGHT));
                reservation.setServices(results.getString(INDEX_RESERVATIONS_SERVICES));
                reservation.setTotal(results.getString(INDEX_RESERVATIONS_TOTAL));
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            System.out.println("Query Failed.");
        }
    }

    public void removeReservation (Reservation reservation) {

        try {
            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM " + TABLE_RESERVATIONS + " WHERE " + COLUMN_RESERVATIONS_ID + "='" + reservation.getId() + "'");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Couldn't delete reservation(s) : " + e.getMessage());
        }
    }

    public boolean addRoom (Room room) {

        try {
            Statement statement = conn.createStatement();
            statement.execute("INSERT INTO " + TABLE_ROOMDETAILS +
                    " (" + COLUMN_ROOMDETAILS_ROOMTYPE + ", " +
                    COLUMN_ROOMDETAILS_ROOMNO + ", " +
                    COLUMN_ROOMDETAILS_MAXPEOPLE + ", " +
                    COLUMN_ROOMDETAILS_FLOORNO + ", " +
                    COLUMN_ROOMDETAILS_PHONENO + ", " +
                    COLUMN_ROOMDETAILS_RATE + ", " +
                    COLUMN_ROOMDETAILS_STATUS + " ) " +
                    "VALUES('" + room.getRoomType() + "', '" + room.getRoomNo() + "', '" + room.getMaxNoOfPeople() + "', '" +
                    room.getFloorNo() + "', '" + room.getPhoneNo() + "', '" + room.getRate() + "', '" +
                    room.getStatus() + "')");
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't add room : " + e.getMessage());
            return false;
        }
    }

    public void loadRooms () {

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_ROOMDETAILS)){

            while (results.next()) {

                Room room = new Room();
                room.setRoomType(results.getString(INDEX_ROOMDETAILS_ROOMTYPE));
                room.setRoomNo(results.getString(INDEX_ROOMDETAILS_ROOMNO));
                room.setMaxNoOfPeople(results.getString(INDEX_ROOMDETAILS_MAXPEOPLE));
                room.setFloorNo(results.getString(INDEX_ROOMDETAILS_FLOORNO));
                room.setPhoneNo(results.getString(INDEX_ROOMDETAILS_PHONENO));
                room.setRate(results.getString(INDEX_ROOMDETAILS_RATE));
                room.setStatus(results.getString(INDEX_ROOMDETAILS_STATUS));
                rooms.add(room);
            }
        } catch (SQLException e) {
            System.out.println("Query Failed.");
        }
    }

    public void removeRoom (Room room) {

        try {
            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM " + TABLE_ROOMDETAILS + " WHERE " + COLUMN_ROOMDETAILS_ROOMNO + "='" + room.getRoomNo() + "'");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Couldn't delete room : " + room.getRoomNo() + " : " + e.getMessage());
        }
    }

    public void updateRoomStatus (Room room, String status) {

        try {
            Statement statement = conn.createStatement();
            statement.execute("UPDATE " + TABLE_ROOMDETAILS + " SET " + COLUMN_ROOMDETAILS_STATUS + "='" + status + "'" + " WHERE " +
                                    COLUMN_ROOMDETAILS_ROOMNO + "='" + room.getRoomNo() + "'");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Couldn't update room status : " + room.getRoomNo() + " : " + e.getMessage());
        }
    }

    public void removeUser (User user) {

        try {
            Statement statement = conn.createStatement();
            statement.execute("DELETE FROM " + TABLE_USERINFO + " WHERE " + COLUMN_USERINFO_ID + "='" + user.getId() + "'");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Couldn't delete room : " + user.getId() + " : " + e.getMessage());
        }
    }
}
