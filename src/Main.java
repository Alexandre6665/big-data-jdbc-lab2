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

        System.out.println("Hello, World!");
    }
}