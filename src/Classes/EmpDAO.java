package Classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpDAO extends DAO<Emp> {
    public EmpDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Emp find(int id) {
        Emp emp = null;
        String query = "SELECT empno, ename, efirst, job, mgr, hiredate, sal, comm, tel, deptno FROM emp WHERE empno = ?";

        try (PreparedStatement preparedStatement = connect.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                emp = new Emp();
                emp.setEmpNo(resultSet.getLong("empno"));
                emp.setEname(resultSet.getString("ename"));
                emp.setEfirst(resultSet.getString("efirst"));
                emp.setJob(resultSet.getString("job"));
                emp.setHireDate(resultSet.getDate("hiredate"));
                emp.setSal(resultSet.getInt("sal"));
                emp.setComm(resultSet.getInt("comm"));
                emp.setTel(resultSet.getInt("tel"));

                // Get the manager empno
                int mgrEmpno = resultSet.getInt("mgr");
                // Get the manager object
                if (mgrEmpno != -1) {
                    Emp manager = find(mgrEmpno);
                    emp.setMgr(manager);
                }

                // Get department information
                int deptno = resultSet.getInt("deptno");
                if (deptno != -1) {
                    DeptDAO deptDAO = new DeptDAO(connect);
                    emp.setDepartment(deptDAO.find(deptno));
                }
            }
            resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return emp;
    }

    // TODO: Implement the logic
    public boolean create(Emp emp) {
        return false;
    }

    public boolean update(Emp emp) {
        return false;
    }

    public boolean delete(Emp emp) {
        return false;
    }
}
