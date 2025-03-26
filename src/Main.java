import java.lang.reflect.Array;
import java.sql.*;


public class Main {
    public static void main(String[] args) {
        /* Load JDBC Driver. */
        try {
            Class.forName( "org.postgresql.Driver" );
        } catch ( ClassNotFoundException e ) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "Courtry2023";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection( url, user, password );
            // displayDepartment( connection );
            // moveDepartment(connection, 7499, 30);
            //displayTable(connection, "emp");

            System.out.println("Db is connected");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                    ignored.printStackTrace();
                }
            }
        }
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
        String query = "UPDATE emp SET deptno = " + deptno + " WHERE empno = " + empno;

        try (Statement statement = connection.createStatement()) {
            int rowsAffected = statement.executeUpdate( query );

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