import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(Utilities.ConnectString, "SA", "")) {
            selectPrintAllPersons(conn);
            selectPrintAllCars(conn);
            selectPrintAllCarOwnerships(conn);
            selectPrintAllOwners(conn);
        }
    }

    private static void selectPrintAllPersons(Connection conn) throws SQLException {
        printHeader("Person");
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(
                "select " +
                "*" +
                "from Person");
            while (rs.next()) {
                int id = rs.getInt("id");
                String lastNames = rs.getString("lastNames");
                String givenNames = rs.getString("givenNames");
                System.out.println(id + ": " + lastNames + ", " + givenNames);
            }
        }
        System.out.println();
    }

    private static void selectPrintAllCars(Connection conn) throws SQLException {
        printHeader("Car");
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                    "select " +
                    "*" +
                    " from Car")) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String make = rs.getString("make");
                String model = rs.getString("model");
                System.out.println(id + ": " + make + "/" + model);
            }
        }
        System.out.println();
    }

    private static void selectPrintAllCarOwnerships(Connection conn) throws SQLException {
        printHeader("CarOwnership");
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                    "select" +
                    " *" +
                    " from CarOwnership")) {
            while (rs.next()) {
                int personId = rs.getInt("personId");
                int carId = rs.getInt("carId");
                System.out.println(personId + " " + carId);
            }
        }
        System.out.println();
    }

    private static void selectPrintAllOwners(Connection conn) throws SQLException {
        printHeader("Owner");
        try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(
                    "select " +
                    "lastNames, make, model " +
                    "from Person " +
                    "join CarOwnership on Person.id = CarOwnership.personId " +
                    "join Car on Car.id = CarOwnership.carId")) {
            while (rs.next()) {
                String lastNames = rs.getString("lastNames");
                String make = rs.getString("make");
                String model = rs.getString("model");
                System.out.println(lastNames + " -> " + make + "/" + model);
            }
        }
        System.out.println();
    }

    private static void printHeader(String s) {
        System.out.println(s);
        for (int i = 0; i < s.length(); ++i)
            System.out.print('-');
        System.out.println();
    }

}
