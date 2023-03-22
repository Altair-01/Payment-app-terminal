package org.example;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    private int id;
    private String name;
    private String department;
    private List<Payroll> payrolls;
    private int totalOvertimeHours;
    private int totalsales;

    public Employee(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.payrolls = new ArrayList<>();
        this.totalOvertimeHours = 0;
        this.totalsales=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Payroll> getPayrolls() {
        return payrolls;
    }

    public void setPayrolls(List<Payroll> payrolls) {
        this.payrolls = payrolls;
    }

    public int getTotalOvertimeHours() {
        return totalOvertimeHours;
    }
    public int getTotalsales() {
        return totalsales;
    }
    public void addPayroll(Payroll payroll) {
        if ((payroll.getHoursWorked() - 44) > 0) {
            totalOvertimeHours += (payroll.getHoursWorked() - 44);
        }
        if (payroll.getSalesTotal() > 0) {
            totalsales += payroll.getSalesTotal();
        }
        payrolls.add(payroll);
    }
}
