package Classes;
import java.util.Date;

public class Emp {
    private Long empNo;

    private String ename;

    private String efirst;

    private String job;

    private Emp mgr;

    private Date hireDate;

    private int sal;

    private int comm;

    private int tel;

    private Dept department;

    public Long getEmpNo() {
        return empNo;
    }

    public void setEmpNo(Long empNo) {
        this.empNo = empNo;
    }

    public String getEname() {
        return this.ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEfirst() {
        return this.efirst;
    }

    public void setEfirst(String efirst) {
        this.efirst = efirst;
    }

    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Date getHireDate() {
        return this.hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public int getSal() {
        return this.sal;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }

    public int getComm() {
        return this.comm;
    }

    public void setComm(int comm) {
        this.comm = comm;
    }

    public int getTel() {
        return this.tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public Dept getDepartment() {
        return this.department;
    }

    public void setDepartment(Dept department) {
        this.department = department;
    }

    public Emp getMgr() {
        return mgr;
    }

    public void setMgr(Emp mgr) {
        this.mgr = mgr;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "empNo=" + empNo +
                ", ename='" + ename + '\'' +
                ", efirst='" + efirst + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + (mgr != null ? mgr.getEmpNo() : "None") + // Print only manager's ID
                ", hireDate=" + hireDate +
                ", sal=" + sal +
                ", comm=" + comm +
                ", tel=" + tel +
                ", department=" + (department != null ? department : "None") +
                '}';
    }
}
