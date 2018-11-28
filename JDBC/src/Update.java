import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Update {
    
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(Utilities.ConnectString, "SA", "")) {
            updatePerson(conn);
        }
    }

    private static void updatePersonLastNames(Statement stmt, int id, String lastNames) throws SQLException {
        stmt.executeUpdate(
                "update " +
                "Person " +
                "set lastNames=" + Utilities.toSQLString(lastNames) + " " +
                "where id=" + id
                );
    }
    
    private static void updatePerson(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            updatePersonLastNames(stmt, 34, "(alias Baffo)");
        }
        System.out.println();
    }

}
