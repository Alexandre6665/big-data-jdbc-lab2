package Classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Courtry2023";
    private Connection connection;
    private static DAOFactory instance;

    private DAOFactory() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public EmpDAO getEmpDAO() {
        return new EmpDAO(connection);
    }

    public DeptDAO getDeptDAO() {
        return new DeptDAO(connection);
    }

    public Connection getConnection() {
        return connection;
    }
}
