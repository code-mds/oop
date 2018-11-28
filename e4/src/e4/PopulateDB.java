package e4;

import java.sql.*;

public class PopulateDB {
    static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(Utilities.getConnString(), "SA", "")) {

            try(PreparedStatement stmt = conn.prepareStatement(
                    "insert into (id,name,salary,managerId)" +
                            "Employee values (" +
                            "?, " +
                            "?, " +
                            "?" +
                            ")")) {
                conn.setAutoCommit(false);

                int id = 101;
                String name = "il Momi";
                double salary = 123.45;
                Integer managerId = null;

                addEmployee(stmt, id, name, salary, managerId);

                conn.setAutoCommit(true);
            }
        }
    }

    private static void addEmployee(PreparedStatement stmt, int id, String name, double salary, Integer managerId) throws SQLException {
        int idx = 1;
        stmt.setInt(idx++, id);
        stmt.setString(idx++, name);
        stmt.setDouble(idx++, salary);
        stmt.setObject(idx++, managerId);
        stmt.executeUpdate();
    }
}
