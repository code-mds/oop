package e4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(Utilities.getConnString(), "SA", "")){
            createTable(conn);
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("CREATE TABLE " +
                    "Employee (" +
                    "id integer primary key," +
                    "name varchar(255)," +
                    "salary numeric," +
                    "managerId integer," +
                    "foreign key (managerId) references Employee (id)" +
                    ")");
        }
    }
}
