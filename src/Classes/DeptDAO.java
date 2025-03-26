package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeptDAO extends DAO<Dept> {
    public DeptDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Dept find(int id) {
        Dept dept = null;

        String query = "SELECT * FROM dept WHERE deptno = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                dept = new Dept();
                dept.setDeptno(resultSet.getInt("deptno"));
                dept.setDname(resultSet.getString("dname"));
                dept.setLoc(resultSet.getString("loc"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return dept;
    }

    // TODO: Implement these functions later
    public boolean create(Dept dept) {
        return false;
    }

    public boolean update(Dept dept) {
        return false;
    }

    public boolean delete(Dept dept) {
        return false;
    }

}
