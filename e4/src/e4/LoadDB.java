package e4;

import java.lang.management.ManagementFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Employee {
    private final int id;
    private final String name;
    private final double salary;
    private List<Employee> directs = new ArrayList<>();

    public Employee(int id, String name, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public List<Employee> getDirects() {
        return directs;
    }

    public void addDirect(Employee direct) {
        this.directs.add(direct);
    }

    @Override
    public String toString() {
        return name + " (" + id + ", " + salary + ')';
    }
}

public class LoadDB {
    public static void main(String[] args) throws SQLException {

        Employee chiefManager = null;
        Map<Integer, Integer> employeeId2managerId = new HashMap<>();
        Map<Integer, Employee> employees = new HashMap<>();

        try (Connection conn = DriverManager.getConnection(Utilities.getConnString(), "SA", "")) {
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery("select * from Employee");
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    double salary = rs.getDouble("salary");
                    Integer managerId = (Integer)rs.getObject("managerId");
                    Employee employee = new Employee(id, name, salary);
                    if(managerId == null)
                        chiefManager = employee;
                    else
                        employeeId2managerId.put(id, managerId);

                    employees.put(id, employee);
                }
            }
        }

        for (Employee e : employees.values()) {
            Integer managerId = employeeId2managerId.get(e.getId());
            Employee manager = employees.get(managerId);

            if(manager != null)
                manager.addDirect(e);
        }


        printTree(chiefManager, "");
    }

    private static void printTree(Employee employee, String pad) {
        System.out.println(pad + employee);

        if(employee.getDirects() != null)
            for (Employee e : employee.getDirects())
                printTree(e, pad + "  ");
    }


}
