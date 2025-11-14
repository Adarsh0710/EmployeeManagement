import java.sql.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Connection con = DBConnection.getConnection();

        if (con == null) {
            System.out.println("Database connection failed!");
            return;
        }

        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Manager");
            System.out.println("2. Add Developer");
            System.out.println("3. Add HR");
            System.out.println("4. Show All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int ch = sc.nextInt();

            try {
                switch (ch) {
                    case 1 -> addManager(con, sc);
                    case 2 -> addDeveloper(con, sc);
                    case 3 -> addHR(con, sc);
                    case 4 -> showAll(con);
                    case 5 -> {
                        System.out.println("Goodbye!");
                        con.close();
                        return;
                    }
                    default -> System.out.println("Invalid choice!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void addManager(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ID, Name, Base Salary, Bonus: ");
        int id = sc.nextInt();
        String name = sc.next();
        double base = sc.nextDouble();
        double bonus = sc.nextDouble();

        Manager m = new Manager(id, name, base, bonus);
        insertEmployee(con, m, bonus);
    }

    private static void addDeveloper(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ID, Name, Base Salary, No. of Projects: ");
        int id = sc.nextInt();
        String name = sc.next();
        double base = sc.nextDouble();
        int projects = sc.nextInt();

        Developer d = new Developer(id, name, base, projects);
        insertEmployee(con, d, projects);
    }

    private static void addHR(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter ID, Name, Base Salary, No. of Recruits: ");
        int id = sc.nextInt();
        String name = sc.next();
        double base = sc.nextDouble();
        int recruits = sc.nextInt();

        HR hr = new HR(id, name, base, recruits);
        insertEmployee(con, hr, recruits);
    }

    private static void insertEmployee(Connection con, Employee emp, double extra) throws SQLException {
        String sql = "INSERT INTO employees (id, name, role, baseSalary, extra, totalSalary) VALUES (?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, emp.getId());
        ps.setString(2, emp.getName());
        ps.setString(3, emp.getRole());
        ps.setDouble(4, emp.getBaseSalary());
        ps.setDouble(5, extra);
        ps.setDouble(6, emp.calculateSalary());
        ps.executeUpdate();
        System.out.println("✅ " + emp.getRole() + " added successfully!");
    }

    private static void showAll(Connection con) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM employees");

        System.out.println("\n--- Employee Records ---");
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + ", Name: " + rs.getString("name") +
                    ", Role: " + rs.getString("role") + ", Total Salary: " + rs.getDouble("totalSalary"));
        }
    }
}
