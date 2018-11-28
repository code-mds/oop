import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class BatchInsert {

    public static void main(String[] args) throws SQLException {
        try (Connection conn = DriverManager.getConnection(Utilities.ConnectString, "SA", "")) {
            populateTablePerson(conn);
        }
    }

    private static void populateTablePerson(Connection conn) throws SQLException {
        conn.setAutoCommit(false);
        try (PreparedStatement stmt = conn.prepareStatement(
                "insert into " +
                "Person values (" +
                    "?, " +
                    "?, " +
                    "?" +
                ")")) {
            for (Person p : carOwners()) {
                stmt.setInt(1, p.getId());
                stmt.setString(2, p.getLastNames());
                stmt.setString(3, p.getFirstNames());
                stmt.executeUpdate();
            }
            conn.commit();
        }
    }

    private static Set<Person> carOwners() {
        HashSet<Person> owners = new HashSet<Person>();
        owners.add(new Person(32, null, "Thant"));
        owners.add(new Person(43, "Pallo", "Pinco"));
        owners.add(new Person(54, "Nurmi", "Paavo Johannes"));
        owners.add(new Person(101, "Ruíz Blasco y Picasso a Lasso Lopez", "Pablo Diego José Santiago Francisco de Paula Juan Nepomuceno Crispin Crispiniano María de los Garnimano Remedios Cipriano de la Santísima Trinidad Martyr Clito")); 
        return owners;
    }
    
}
