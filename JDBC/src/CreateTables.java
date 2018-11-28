import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(Utilities.ConnectString, "SA", "")) {
            createTablePerson(conn);
            createTableCar(conn);
            createTableCarOwnership(conn);
            populateTablePerson(conn);
            populateTableCar(conn);
            populateTableCarOwnership(conn);
        }
    }

    private static void createTablePerson(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                    "create table " +
                    "Person (" +
                        "id integer primary key," +
                        "lastNames varchar(255)," +
                        "givenNames varchar(255)" +
                    ")");
        }
    }

    private static void createTableCar(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                    "create table " +
                    "Car (" +
                        "id integer primary key," +
                        "make varchar(255)," +
                        "model varchar(255)" +
                    ")");
        }
    }
    
    private static void createTableCarOwnership(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(
                    "create table " +
                    "CarOwnership (" +
                        "personId integer," +
                        "carId integer," +
                        "foreign key (personId) references Person (id)," +
                        "foreign key (carId) references Car (id)" +
                    ")");
        }
    }
    
    private static void insertPerson(Statement stmt, int id, String lastNames, String firstNames) throws SQLException {
        stmt.executeUpdate(
                "insert into " +
                "Person values (" +
                    id + "," +
                    Utilities.toSQLString(lastNames) + "," +
                    Utilities.toSQLString(firstNames) +
                ")");
    }
    
    private static void populateTablePerson(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            insertPerson(stmt, 23, "Sant'Agata", "Nicolaus");
            insertPerson(stmt, 24, "Толсто́й", "Лев Никола́евич");
            insertPerson(stmt, 34, "Böll", "Heinrich");
            insertPerson(stmt, 45, "Proust", "Valentin Louis Georges Eugène Marcel");
        }
    }

    private static void insertCar(Statement stmt, int id, String make, String model) throws SQLException {
        stmt.executeUpdate(
                "insert into " +
                "Car values (" +
                    id + "," +
                    Utilities.toSQLString(make) + "," +
                    Utilities.toSQLString(model) +
                ")");
    }
    
    private static void populateTableCar(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            insertCar(stmt, 1001, "Fiat", "Panda");
            insertCar(stmt, 1002, "Opel", "Corsa");
            insertCar(stmt, 1003, "Renault", "Twingo");
        }
    }

    private static void insertCarOwnership(Statement stmt, int carId, int personId) throws SQLException {
        stmt.executeUpdate(
                "insert into " +
                "CarOwnership values (" +
                    carId + "," +
                    personId +
                ")");
    }
    
    private static void populateTableCarOwnership(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            insertCarOwnership(stmt, 23, 1001);
            insertCarOwnership(stmt, 23, 1002);
            insertCarOwnership(stmt, 34, 1002);
        }
    }

}
