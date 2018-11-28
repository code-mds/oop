import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Delete {
    
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(Utilities.ConnectString, "SA", "")) {
            deletePerson(conn);
        }
    }

    private static void deletePerson(Statement stmt, int id) throws SQLException {
        stmt.executeUpdate(
                "delete " +
                "from Person " +
                "where id=" + id
                );
                
    }
    
    private static void deletePerson(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            deletePerson(stmt, 23);
        }
        System.out.println();
    }

}
