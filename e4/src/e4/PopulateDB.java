package e4;

import java.sql.*;

public class PopulateDB {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(Utilities.getConnString(), "SA", "")) {

            try(PreparedStatement stmt = conn.prepareStatement(
                    "insert into Employee (id,name,salary,managerId) " +
                            " values (" +
                            "?, " +
                            "?, " +
                            "?, " +
                            "?)")) {
                conn.setAutoCommit(false);

                addEmployee(stmt, 101, "il Momi", 123.45, null);
                    addEmployee(stmt, 113, "Andrea", 113.45, 101);
                        addEmployee(stmt, 313, "Svetlanka", 123.45, 113);
                        addEmployee(stmt, 222, "Markko", 123.45, 113);
                    addEmployee(stmt, 747, "Jakopo", 113.45, 101);
                        addEmployee(stmt, 606, "Jamal", 103.45, 747);
                        addEmployee(stmt, 666, "Flora", 103.45, 747);
                        addEmployee(stmt, 28, "Frank", 103.45, 747);
                    addEmployee(stmt, 155, "Johnny", 113.45, 101);
                        addEmployee(stmt, 983, "Vladimir", 103.45, 155);
                        addEmployee(stmt, 635, "Mara", 103.45, 155);
                        addEmployee(stmt, 870, "Akinori", 103.45, 155);

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
