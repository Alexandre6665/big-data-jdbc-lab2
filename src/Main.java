import Classes.*;

import java.sql.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        DAOFactory daoFactory = DAOFactory.getInstance();

        EmpDAO empDAO = daoFactory.getEmpDAO();

        Emp emp7521 = empDAO.find(7521);
        System.out.println(emp7521);

        DeptDAO deptDAO = daoFactory.getDeptDAO();

        Dept dept20 = deptDAO.find(20);
        System.out.println(dept20);

        // Old code before using DAOFactory
        /* Load JDBC Driver. */
        /*try {
            Class.forName( "org.postgresql.Driver" );
        } catch ( ClassNotFoundException e ) {
            System.out.println( e.getMessage() );
        }

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "Courtry2023";
        Connection connection = null;

        try {
            // Connection to the database
            connection = DriverManager.getConnection( url, user, password );

            // Retrieving information of department 20
            DAO<Dept> departmentDAO = new DeptDAO( connection );
            Dept dept20 = departmentDAO.find( 20 );
            System.out.println( dept20 );

            // Retrieving data about employee 7521
            DAO<Emp> empDAO = new EmpDAO( connection );
            Emp emp7521 = empDAO.find( 7521 );
            System.out.println( emp7521 );
        } catch (SQLException e) {
            System.out.println( e.getMessage() );
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignore) {
                    System.out.println( ignore.getMessage() );
                }
            }
        }*/
    }

    public static void displayDepartment(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery( "SELECT * FROM dept" );

        while (resultSet.next()) {
            int deptno = resultSet.getInt( "deptno" );
            String dname = resultSet.getString( "dname" );
            String location = resultSet.getString("loc");

            System.out.println("Department " + deptno + " is for "
                    + dname + "located in " + location);
        }
        resultSet.close();
    }

    public static void moveDepartment(Connection connection, int empno, int deptno) throws SQLException {
        String query = "UPDATE emp SET deptno = ? WHERE empno = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            Scanner scanner = new Scanner(System.in);

            preparedStatement.setInt(1, deptno);
            preparedStatement.setInt(2, empno);

            int rowsAffected = preparedStatement.executeUpdate();

            // Check if the employee was moved
            if (rowsAffected > 0) {
                System.out.println("Employee " + empno + " moved to department " + deptno);
            } else {
                System.out.println("No employee found with empno " + empno);
            }
        } catch (SQLException e) {
            System.out.println("Failed to move employee: " + e.getMessage());
            throw e;
        }
    }

    public static void displayTable(Connection connection, String tableName) throws SQLException {
        String query = "SELECT * FROM " + tableName;

        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Failed to display table: " + e.getMessage());
            throw e;
        }
    }
}